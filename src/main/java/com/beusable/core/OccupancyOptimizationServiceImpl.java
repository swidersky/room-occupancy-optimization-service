package com.beusable.core;

import com.beusable.application.OccupancyOptimizationService;
import com.beusable.core.model.RoomOccupancyDetails;
import com.beusable.core.model.OccupancyOptimization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class OccupancyOptimizationServiceImpl implements OccupancyOptimizationService {

    private final GuestListFilter guestListFilter;

    @Override
    public OccupancyOptimization optimize(int availablePremiumRooms, int availableEconomyRooms, List<Float> guestPayments) {

        RoomOccupancyDetails economicRoomDetails = new RoomOccupancyDetails();
        RoomOccupancyDetails premiumRoomDetails = new RoomOccupancyDetails();



        List<Float> guestsPaymentsOver100
                = guestListFilter.filter(guestPayments, paymentRequest -> paymentRequest >= 100);
        List<Float> guestsPaymentsLess100
                = guestListFilter.filter(guestPayments, paymentRequest -> paymentRequest < 100);


        guestsPaymentsOver100.stream()
                .limit(availablePremiumRooms)
                .forEach(updateRoomOccupancyDetails(premiumRoomDetails));

        int freePremiumRoomsLeft = availablePremiumRooms - premiumRoomDetails.getRoomUsed();
        int roomsNeededInEconomyClass = Math.abs(availableEconomyRooms - guestsPaymentsLess100.size());

        boolean areEconomyRoomsFullyBooked = availableEconomyRooms - guestsPaymentsLess100.size() < 0;

        if (areEconomyRoomsFullyBooked) {

            guestsPaymentsLess100.stream()
                    .limit(Math.min(freePremiumRoomsLeft, roomsNeededInEconomyClass))
                    .forEach(updateRoomOccupancyDetails(premiumRoomDetails));

            guestsPaymentsLess100.stream()
                    .skip(Math.min(freePremiumRoomsLeft, roomsNeededInEconomyClass))
                    .limit(availableEconomyRooms)
                    .forEach(updateRoomOccupancyDetails(economicRoomDetails));

        } else {

            guestsPaymentsLess100.stream()
                    .limit(availableEconomyRooms)
                    .forEach(updateRoomOccupancyDetails(economicRoomDetails));

        }

        return new OccupancyOptimization(premiumRoomDetails, economicRoomDetails);

    }

    private Consumer<Float> updateRoomOccupancyDetails(RoomOccupancyDetails roomOccupancyDetails) {
        return guestsPaymentRequest -> {
            roomOccupancyDetails.setRoomUsed(roomOccupancyDetails.getRoomUsed() + 1);
            roomOccupancyDetails.setTotalAmount(MathHelper.round(roomOccupancyDetails.getTotalAmount() + guestsPaymentRequest, 2));
        };
    }

}

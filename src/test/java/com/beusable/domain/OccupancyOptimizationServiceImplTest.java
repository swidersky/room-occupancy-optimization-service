package com.beusable.domain;

import com.beusable.core.GuestListFilter;
import com.beusable.core.OccupancyOptimizationServiceImpl;
import com.beusable.core.model.OccupancyOptimization;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OccupancyOptimizationServiceImplTest {

    private GuestListFilter guestListFilter = new GuestListFilter();
    private final OccupancyOptimizationServiceImpl occupancyOptimizationService = new OccupancyOptimizationServiceImpl(guestListFilter);
    List<Float> guestsPaymentRequests = List.of(23f, 45f, 155f, 374f, 22f, 99.99f, 100f, 101f, 115f, 209f);


    @Test
    public void when3PremiumRoomsAnd3EconomyRoomsAreAvailableReturnCorrectValues() {

        OccupancyOptimization occupancyOptimization = occupancyOptimizationService.optimize(3, 3, guestsPaymentRequests);

        OccupancyExpectedResult occupancyExpectedResult = OccupancyExpectedResult.builder()
                .premiumRoomUsed(3)
                .economyRoomUsed(3)
                .premiumTotalAmount(738.0)
                .economyTotalAmount(167.99)
                .build();

        assertResult(occupancyOptimization, occupancyExpectedResult);

    }

    @Test
    public void when7PremiumRoomsAnd5EconomyRoomsAreAvailableReturnCorrectValues() {

        OccupancyOptimization occupancyOptimization = occupancyOptimizationService.optimize(7, 5, guestsPaymentRequests);

        OccupancyExpectedResult occupancyExpectedResult = OccupancyExpectedResult.builder()
                .premiumRoomUsed(6)
                .economyRoomUsed(4)
                .premiumTotalAmount(1054)
                .economyTotalAmount(189.99)
                .build();

        assertResult(occupancyOptimization, occupancyExpectedResult);

    }

    @Test
    public void when2PremiumRoomsAnd7EconomyRoomsAreAvailableReturnCorrectValues() {

        OccupancyOptimization occupancyOptimization = occupancyOptimizationService.optimize(2, 7, guestsPaymentRequests);

        OccupancyExpectedResult occupancyExpectedResult = OccupancyExpectedResult.builder()
                .premiumRoomUsed(2)
                .economyRoomUsed(4)
                .premiumTotalAmount(583.0)
                .economyTotalAmount(189.99)
                .build();

        assertResult(occupancyOptimization, occupancyExpectedResult);

    }

    @Test
    public void when7PremiumRoomsAnd1EconomyRoomsAreAvailableReturnCorrectValues() {

        OccupancyOptimization occupancyOptimization = occupancyOptimizationService.optimize(7, 1, guestsPaymentRequests);

        OccupancyExpectedResult occupancyExpectedResult = OccupancyExpectedResult.builder()
                .premiumRoomUsed(7)
                .economyRoomUsed(1)
                .premiumTotalAmount(1153.99)
                .economyTotalAmount(45.0)
                .build();

        assertResult(occupancyOptimization, occupancyExpectedResult);

    }

    private void assertResult(OccupancyOptimization occupancyOptimization, OccupancyExpectedResult occupancyExpectedResult) {
        assertEquals(occupancyExpectedResult.getPremiumTotalAmount(), occupancyOptimization.getPremiumRoomDetails().getTotalAmount());
        assertEquals(occupancyExpectedResult.getEconomyTotalAmount(), occupancyOptimization.getEconomyRoomDetails().getTotalAmount());
        assertEquals(occupancyExpectedResult.getPremiumRoomUsed(), occupancyOptimization.getPremiumRoomDetails().getRoomUsed());
        assertEquals(occupancyExpectedResult.getEconomyRoomUsed(), occupancyOptimization.getEconomyRoomDetails().getRoomUsed());
    }

}

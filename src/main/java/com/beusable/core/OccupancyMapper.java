package com.beusable.core;

import com.beusable.core.model.OccupancyOptimization;
import com.beusable.core.model.RoomOccupancyDetails;
import com.beusable.model.OptimizationResponse;
import com.beusable.model.RoomOccupancyResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class OccupancyMapper {

    public OptimizationResponse toDTO(OccupancyOptimization occupancyOptimization){

        OptimizationResponse optimizationResponse = new OptimizationResponse();

        optimizationResponse.setEconomyRoomDetails(mapRoomsDetails(occupancyOptimization.getEconomyRoomDetails()));
        optimizationResponse.setPremiumRoomDetails(mapRoomsDetails(occupancyOptimization.getPremiumRoomDetails()));

        return optimizationResponse;

    }

    private RoomOccupancyResponse mapRoomsDetails(RoomOccupancyDetails economyRoomDetails) {

        RoomOccupancyResponse roomOccupancyResponse = new RoomOccupancyResponse();

        roomOccupancyResponse.setRoomUsed(economyRoomDetails.getRoomUsed());
        roomOccupancyResponse.setTotalAmount(new BigDecimal(economyRoomDetails.getTotalAmount()).setScale(2, RoundingMode.HALF_UP));

        return roomOccupancyResponse;
    }

}

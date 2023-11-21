package com.beusable.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OccupancyOptimization {

    private RoomOccupancyDetails premiumRoomDetails;
    private RoomOccupancyDetails economyRoomDetails;

}

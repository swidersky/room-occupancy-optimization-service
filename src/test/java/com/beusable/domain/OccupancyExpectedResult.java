package com.beusable.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OccupancyExpectedResult {

    private int premiumRoomUsed;
    private double premiumTotalAmount;
    private int economyRoomUsed;
    private double economyTotalAmount;

}

package com.beusable.application;

import com.beusable.core.model.OccupancyOptimization;

import java.util.List;

public interface OccupancyOptimizationService {

    OccupancyOptimization optimize(int availablePremiumRooms, int availableEconomyRooms, List<Float> guestsPaymentRequests);

}

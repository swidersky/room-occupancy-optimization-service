package com.beusable.web;

import com.beusable.api.OptimizationsApi;
import com.beusable.application.OccupancyOptimizationService;
import com.beusable.core.OccupancyMapper;
import com.beusable.model.GenerateOptimizationRequest;
import com.beusable.model.OptimizationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OccupancyOptimizationController implements OptimizationsApi {

    private final OccupancyOptimizationService occupancyOptimizationService;
    private final OccupancyMapper occupancyMapper;

    @Override
    public ResponseEntity<OptimizationResponse> generateOptimization(GenerateOptimizationRequest generateOptimizationRequest) {
        return ResponseEntity.ok(occupancyMapper.toDTO(occupancyOptimizationService.optimize(generateOptimizationRequest.getAmountOfPremiumRooms(),
                generateOptimizationRequest.getAmountOfEconomyRooms(),
                generateOptimizationRequest.getGuestPayments())));
    }

}

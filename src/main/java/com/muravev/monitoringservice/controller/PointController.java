package com.muravev.monitoringservice.controller;

import com.muravev.monitoringservice.model.request.MapViewRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;
import com.muravev.monitoringservice.service.impl.ActualPointCoordinator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/geo-points")
@RequiredArgsConstructor
public class PointController {
    private final ActualPointCoordinator actualPointCoordinator;


    @PostMapping(params = "actual")
    public List<EquipmentPointResponse> getActualGeoLocations(@Valid @RequestBody MapViewRequest request) {
        return actualPointCoordinator.getActualGeolocations(request);
    }
}

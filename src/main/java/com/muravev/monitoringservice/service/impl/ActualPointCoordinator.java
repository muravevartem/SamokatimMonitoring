package com.muravev.monitoringservice.service.impl;

import com.muravev.monitoringservice.dao.EquipmentCurrentGeopositionRepository;
import com.muravev.monitoringservice.mapper.GeolocationMapper;
import com.muravev.monitoringservice.model.request.MapViewRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;
import com.muravev.monitoringservice.service.PointCoordinator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActualPointCoordinator implements PointCoordinator {
    private final EquipmentCurrentGeopositionRepository currentGeopositionRepository;
    private final GeolocationMapper geolocationMapper;


    @Override
    public List<EquipmentPointResponse> getActualGeolocations(MapViewRequest viewRequest) {
        var northEast = viewRequest.getNorthEast();
        var southWest = viewRequest.getSouthWest();
        var equipments = currentGeopositionRepository.findAllBySearchPerimeter(
                northEast.getLat(),
                northEast.getLng(),
                southWest.getLat(),
                southWest.getLng()
        );
        return equipments.stream()
                .map(geolocationMapper::toGeolocationResponse)
                .collect(Collectors.toList());
    }
}

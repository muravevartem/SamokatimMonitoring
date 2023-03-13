package com.muravev.monitoringservice.service;

import com.muravev.monitoringservice.model.request.MapViewRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;

import java.util.List;

public interface PointCoordinator {
    List<EquipmentPointResponse> getActualGeolocations(MapViewRequest viewRequest);
}

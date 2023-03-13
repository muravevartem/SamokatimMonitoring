package com.muravev.monitoringservice.service;

import com.muravev.monitoringservice.model.request.EquipmentGeolocationRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;

public interface EquipmentMonitor {
    EquipmentPointResponse saveGeolocation(EquipmentGeolocationRequest geolocationRequest);
}

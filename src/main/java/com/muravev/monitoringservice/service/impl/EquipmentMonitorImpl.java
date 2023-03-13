package com.muravev.monitoringservice.service.impl;

import com.muravev.monitoringservice.dao.EquipmentCurrentGeopositionRepository;
import com.muravev.monitoringservice.dao.EquipmentGeoTrackRepository;
import com.muravev.monitoringservice.document.EquipmentGeoTrackPoint;
import com.muravev.monitoringservice.mapper.GeolocationMapper;
import com.muravev.monitoringservice.model.request.EquipmentGeolocationRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;
import com.muravev.monitoringservice.service.EquipmentMonitor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentMonitorImpl implements EquipmentMonitor {
    private final EquipmentCurrentGeopositionRepository currentGeoPositionRepository;
    private final EquipmentGeoTrackRepository trackRepository;
    private final GeolocationMapper geolocationMapper;


    @Override
    public EquipmentPointResponse saveGeolocation(EquipmentGeolocationRequest geolocationRequest) {
        var source = geolocationMapper.toCurrentLocation(geolocationRequest);
        source.setLastModifiedAt(LocalDateTime.now());
        var currentGeolocationForSave = currentGeoPositionRepository.findByTransportId(source.getTransportId())
                .map(target -> geolocationMapper.merge(target, source))
                .orElse(source.setId(UUID.randomUUID()));
        var savedGeolocation = currentGeoPositionRepository.save(currentGeolocationForSave);
        saveTrackPoint(geolocationRequest);
        return geolocationMapper.toGeolocationResponse(savedGeolocation);
    }

    private void saveTrackPoint(EquipmentGeolocationRequest geolocationRequest) {
        var point = geolocationMapper.toTrackPoint(geolocationRequest);
        point.setAddedAt(LocalDateTime.now())
                .setId(UUID.randomUUID());
        trackRepository.save(point);
        log.info("Saved track point for equipment {}", geolocationRequest.getTransportId());
    }

}

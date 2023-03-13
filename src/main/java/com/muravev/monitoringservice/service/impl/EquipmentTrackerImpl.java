package com.muravev.monitoringservice.service.impl;

import com.muravev.monitoringservice.dao.EquipmentGeoTrackRepository;
import com.muravev.monitoringservice.mapper.GeolocationMapper;
import com.muravev.monitoringservice.model.response.EquipmentTrackResponse;
import com.muravev.monitoringservice.service.EquipmentTracker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentTrackerImpl implements EquipmentTracker {
    private final EquipmentGeoTrackRepository trackRepository;
    private final GeolocationMapper geolocationMapper;

    @Override
    public List<EquipmentTrackResponse> getByEquipment(long equipmentId, LocalDateTime start, LocalDateTime end) {
        var track = trackRepository.findAllByTransportId(equipmentId,
                start,
                end,
                Sort.by("addedAt").descending()
        );
        return track.stream()
                .map(geolocationMapper::toGeolocationResponse)
                .collect(Collectors.toList());
    }
}

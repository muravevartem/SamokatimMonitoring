package com.muravev.monitoringservice.controller;

import com.muravev.monitoringservice.model.request.EquipmentGeolocationRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;
import com.muravev.monitoringservice.model.response.EquipmentTrackResponse;
import com.muravev.monitoringservice.service.EquipmentMonitor;
import com.muravev.monitoringservice.service.EquipmentTracker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tracks")
@RequiredArgsConstructor
public class TrackController {
    private final EquipmentMonitor monitor;
    private final EquipmentTracker tracker;


    @PostMapping
    public EquipmentPointResponse create(@Valid @RequestBody EquipmentGeolocationRequest request) {
        return monitor.saveGeolocation(request);
    }

    @GetMapping
    public List<EquipmentTrackResponse> getByEquipment(@RequestParam Long tId,
                                                       @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
                                                       @RequestParam OffsetDateTime start,
                                                       @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
                                                       @RequestParam OffsetDateTime end) {
        return tracker.getByEquipment(tId, start.toLocalDateTime(), end.toLocalDateTime());
    }
}

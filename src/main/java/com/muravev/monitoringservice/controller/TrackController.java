package com.muravev.monitoringservice.controller;

import com.muravev.monitoringservice.document.TransportTimePoint;
import com.muravev.monitoringservice.model.TransportPoint;
import com.muravev.monitoringservice.service.TransportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/track")
@RequiredArgsConstructor
public class TrackController {
    private final TransportService transportService;


    @GetMapping
    public Flux<TransportTimePoint> getTrack(@RequestParam("t") Long transportId) {
        return transportService.getTrack(transportId);
    }

    @GetMapping("/last")
    public Mono<TransportTimePoint> getLastPoint(@RequestParam("t") Long transportId) {
        return transportService.lastPoint(transportId);
    }


    @PostMapping
    public Mono<TransportTimePoint> savePoint(@RequestBody @Valid TransportPoint point) {
        return transportService.savePoint(point);
    }
}

package com.muravev.monitoringservice.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EquipmentTrackResponse {
    private UUID id;
    private Long transportId;
    private Double lat;
    private Double lng;
    private LocalDateTime addedAt;
}

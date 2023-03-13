package com.muravev.monitoringservice.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document
public class EquipmentGeoTrackPoint {
    @Id
    private UUID id;
    private Double lat;
    private Double lng;
    private Long transportId;
    private LocalDateTime addedAt;
}

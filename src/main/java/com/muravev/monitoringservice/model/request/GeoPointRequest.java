package com.muravev.monitoringservice.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GeoPointRequest {
    @NotNull
    private Double lat;

    @NotNull
    private Double lng;
}

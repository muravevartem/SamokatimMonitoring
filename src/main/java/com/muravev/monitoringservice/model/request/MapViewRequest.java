package com.muravev.monitoringservice.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MapViewRequest {
    @Valid
    @NotNull
    private GeoPointRequest northEast;

    @Valid
    @NotNull
    private GeoPointRequest southWest;
}

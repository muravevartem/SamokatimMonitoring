package com.muravev.monitoringservice.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EquipmentGeolocationRequest {
    @NotNull
    @Positive
    private Long transportId;
    @NotNull
    private Double lat;

    @NotNull
    private Double lng;
}

package com.muravev.monitoringservice.model.response;

import lombok.Data;

@Data
public class EquipmentPointResponse {
    private Long transportId;
    private Double lat;
    private Double lng;
}

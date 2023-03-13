package com.muravev.monitoringservice.mapper;

import com.muravev.monitoringservice.document.EquipmentCurrentGeolocation;
import com.muravev.monitoringservice.document.EquipmentGeoTrackPoint;
import com.muravev.monitoringservice.model.request.EquipmentGeolocationRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;
import com.muravev.monitoringservice.model.response.EquipmentTrackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.annotation.Id;

@Mapper
public interface GeolocationMapper {
    EquipmentCurrentGeolocation toCurrentLocation(EquipmentGeolocationRequest request);

    EquipmentGeoTrackPoint toTrackPoint(EquipmentGeolocationRequest request);

    EquipmentPointResponse toGeolocationResponse(EquipmentCurrentGeolocation document);

    EquipmentTrackResponse toGeolocationResponse(EquipmentGeoTrackPoint trackPoint);

    @Mapping(target = "id", ignore = true)
    EquipmentCurrentGeolocation merge(@MappingTarget EquipmentCurrentGeolocation target, EquipmentCurrentGeolocation source);
}

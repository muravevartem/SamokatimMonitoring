package com.muravev.monitoringservice.dao;

import com.muravev.monitoringservice.document.EquipmentCurrentGeolocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipmentCurrentGeopositionRepository extends MongoRepository<EquipmentCurrentGeolocation, UUID> {

    @Query("{'lat': {$lt: ?0, $gt: ?2}, 'lng': {$lt: ?1, $gt: ?3}}")
    List<EquipmentCurrentGeolocation> findAllBySearchPerimeter(double latNE, double lngNE, double latSW, double lnSW);

    Optional<EquipmentCurrentGeolocation> findByTransportId(Long transportId);
}

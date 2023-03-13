package com.muravev.monitoringservice.dao;

import com.muravev.monitoringservice.document.EquipmentGeoTrackPoint;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EquipmentGeoTrackRepository extends MongoRepository<EquipmentGeoTrackPoint, UUID> {

    @Query("{'transportId': ?0, 'addedAt': {$gt: ?1, $lt: ?2}}")
    List<EquipmentGeoTrackPoint> findAllByTransportId(Long transportId,
                                                      LocalDateTime start,
                                                      LocalDateTime end,
                                                      Sort sort);

}

package com.muravev.monitoringservice.mqtt.service.impl;

import com.muravev.monitoringservice.model.request.EquipmentGeolocationRequest;
import com.muravev.monitoringservice.mqtt.service.MqttHandler;
import com.muravev.monitoringservice.service.EquipmentMonitor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeolocationMqttHandler implements MqttHandler {
    private final EquipmentMonitor monitor;


    @Override
    public void handle(Message<?> message) {
        var geoMessage = String.valueOf(message.getPayload());

        String[] splitMessage = geoMessage.split(";");
        if (splitMessage.length != GeolocationMessageStructure.values().length) {
            log.warn("[MQTT-GEO] Invalid message");
            return;
        }

        var messageType = MqttMessageType.fromString(splitMessage[0])
                .orElse(null);

        if (messageType != MqttMessageType.GEO) {
            log.warn("[MQTT-GEO] Invalid message type {}", splitMessage[0]);
            return;
        }

        var geoPoint = new EquipmentGeolocationRequest()
                .setTransportId(Long.parseLong(splitMessage[GeolocationMessageStructure.CLIENT_ID.ordinal()]))
                .setLng(Double.parseDouble(splitMessage[GeolocationMessageStructure.LNG.ordinal()]))
                .setLat(Double.parseDouble(splitMessage[GeolocationMessageStructure.LAT.ordinal()]));

        var savedPoint = monitor.saveGeolocation(geoPoint);

        log.info("[MQTT-GEO] Saved point lat:{} lng:{}", savedPoint.getLat(), savedPoint.getLng());


    }
}

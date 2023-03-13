package com.muravev.monitoringservice.mqtt.service.impl;

import com.muravev.monitoringservice.mqtt.service.MqttHandler;
import com.muravev.monitoringservice.mqtt.service.MqttHandlingManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultMqttHandlingManager implements MqttHandlingManager {
    private final List<MqttHandler> handlers;


    @Override
    public void handleMessage(Message<?> message) {
        for (var handler : handlers) {
            try {
                handler.handle(message);
            } catch (Exception e) {
                log.error("Error handling MQTT message", e);
            }
        }
    }
}

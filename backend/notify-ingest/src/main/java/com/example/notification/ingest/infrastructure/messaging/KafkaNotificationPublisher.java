package com.example.notification.ingest.infrastructure.messaging;

import com.example.notification.common.Constants;
import com.example.notification.common.event.NotificationEvent;
import com.example.notification.common.messaging.EventPublishException;
import com.example.notification.common.messaging.EventPublisher;
import com.example.notification.ingest.application.port.out.NotificationPublisherPort;
import com.example.notification.ingest.infrastructure.messaging.dto.NotificationEventPayload;
import com.example.notification.ingest.infrastructure.messaging.mapper.NotificationEventMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Мок-реализация публикации уведомлений.
 * Реальный Kafka-клиент будет подключён на следующих этапах.
 */
@Slf4j
@ApplicationScoped
public class KafkaNotificationPublisher implements NotificationPublisherPort,
        EventPublisher<NotificationEvent> {

    private final String topic;
    private final ObjectMapper objectMapper;
    private final NotificationEventMapper eventMapper;

    public KafkaNotificationPublisher(
            @ConfigProperty(name = "app.notify.topic", defaultValue = Constants.NOTIFY_TOPIC)
            String topic,
            ObjectMapper objectMapper,
            NotificationEventMapper eventMapper
    ) {
        this.topic = topic;
        this.objectMapper = objectMapper;
        this.eventMapper = eventMapper;
    }

    @Override
    public void publish(NotificationEvent event) {
        publish(topic, event.getId(), event);
    }

    @Override
    public void publish(String destination, String key, NotificationEvent event) {
        try {
            // Map common model to Kafka payload DTO
            NotificationEventPayload payload = eventMapper.toPayload(event);
            String json = objectMapper.writeValueAsString(payload);

            // MOCK: только логирование, реальная отправка позже
            log.info("[MOCK] Publishing to Kafka: destination={}, key={}, payload={}",
                    destination, key, json);

        } catch (JsonProcessingException e) {
            throw new EventPublishException("Failed to serialize event", e);
        }
    }
}

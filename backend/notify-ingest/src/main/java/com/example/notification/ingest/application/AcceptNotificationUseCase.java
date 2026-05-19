package com.example.notification.ingest.application;

import com.example.notification.common.event.NotificationEvent;
import com.example.notification.ingest.application.port.in.AcceptNotificationPort;
import com.example.notification.ingest.application.port.out.NotificationPublisherPort;
import com.example.notification.ingest.domain.IngestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Use case: приём уведомления и отправка в брокер.
 */
@Service
public class AcceptNotificationUseCase implements AcceptNotificationPort {

    private static final Logger log = LoggerFactory.getLogger(AcceptNotificationUseCase.class);

    private final NotificationPublisherPort notificationPublisher;

    public AcceptNotificationUseCase(NotificationPublisherPort notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }

    @Override
    public IngestResult accept(NotificationEvent event) {
        log.debug("Accepting notification: id={}, code={}", event.getId(), event.getCode());

        notificationPublisher.publish(event);

        log.info("Notification accepted: id={}", event.getId());
        return IngestResult.ACCEPTED;
    }
}

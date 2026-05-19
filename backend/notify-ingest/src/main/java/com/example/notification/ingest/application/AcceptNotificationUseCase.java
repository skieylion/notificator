package com.example.notification.ingest.application;

import com.example.notification.common.event.NotificationEvent;
import com.example.notification.ingest.application.port.in.AcceptNotificationPort;
import com.example.notification.ingest.application.port.out.NotificationPublisherPort;
import com.example.notification.ingest.domain.IngestResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Use case: приём уведомления и отправка в брокер.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AcceptNotificationUseCase implements AcceptNotificationPort {

    private final NotificationPublisherPort notificationPublisher;

    @Override
    public IngestResult accept(NotificationEvent event) {
        log.debug("Accepting notification: id={}, code={}", event.getId(), event.getCode());

        notificationPublisher.publish(event);

        log.info("Notification accepted: id={}", event.getId());
        return IngestResult.ACCEPTED;
    }
}

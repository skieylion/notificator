package com.example.notification.ingest.application.port.in;

import com.example.notification.common.event.NotificationEvent;
import com.example.notification.ingest.domain.IngestResult;

/**
 * Порт для приёма уведомлений.
 */
public interface AcceptNotificationPort {
    /**
     * Принимает уведомление и отправляет в брокер.
     *
     * @param event событие уведомления (уже смаппленное из DTO)
     * @return результат приёма
     */
    IngestResult accept(NotificationEvent event);
}

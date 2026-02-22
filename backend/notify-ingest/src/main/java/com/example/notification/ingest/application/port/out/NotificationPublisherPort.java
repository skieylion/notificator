package com.example.notification.ingest.application.port.out;

import com.example.notification.common.event.NotificationEvent;

/**
 * Порт для публикации уведомлений в брокер.
 */
public interface NotificationPublisherPort {
    /**
     * Публикует уведомление.
     *
     * @param event событие уведомления
     */
    void publish(NotificationEvent event);
}

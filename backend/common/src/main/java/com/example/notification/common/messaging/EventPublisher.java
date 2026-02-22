package com.example.notification.common.messaging;

/**
 * Порт для публикации событий в брокер сообщений.
 * Реализации: Kafka, RabbitMQ, mock и т.д.
 *
 * @param <T> тип payload события
 */
public interface EventPublisher<T> {
    /**
     * Публикует событие в брокер.
     *
     * @param destination топик/очередь
     * @param key ключ сообщения (для партиционирования)
     * @param payload тело события
     * @throws EventPublishException при ошибке публикации
     */
    void publish(String destination, String key, T payload);
}

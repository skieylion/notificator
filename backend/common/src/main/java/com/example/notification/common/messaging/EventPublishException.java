package com.example.notification.common.messaging;

/**
 * Исключение при ошибке публикации события.
 */
public class EventPublishException extends RuntimeException {
    public EventPublishException(String message) {
        super(message);
    }

    public EventPublishException(String message, Throwable cause) {
        super(message, cause);
    }
}

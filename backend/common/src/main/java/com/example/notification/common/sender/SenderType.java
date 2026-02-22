package com.example.notification.common.sender;

/**
 * Типы отправителей (инициаторов) уведомлений.
 */
public enum SenderType {
    /** Реальный пользователь системы */
    USER,

    /** Системный сервис (scheduler, billing и т.д.) */
    SYSTEM
}

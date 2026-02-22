package com.example.notification.common.sender;

import java.io.Serializable;

/**
 * Инициатор отправки уведомления.
 */
public interface Sender extends Serializable {

    /**
     * Тип отправителя.
     */
    SenderType getType();

    /**
     * Идентификатор отправителя.
     */
    String getId();
}

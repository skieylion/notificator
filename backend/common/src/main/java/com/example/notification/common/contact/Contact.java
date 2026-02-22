package com.example.notification.common.contact;

import java.io.Serializable;

/**
 * Базовый интерфейс контакта.
 *
 * Контакт — это способ идентификации получателя.
 * Может быть прямым (email, phone) или требующим резолва (userId).
 */
public interface Contact extends Serializable {

    /**
     * Тип контакта.
     */
    ContactType getType();
}

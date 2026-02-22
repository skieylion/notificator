package com.example.notification.common.contact;

/**
 * Типы контактов.
 *
 * Контакт — это не обязательно канал доставки.
 * Например PHONE может использоваться для SMS или Telegram,
 * а USER_ID резолвится dispatcher'ом в реальные контакты.
 */
public enum ContactType {
    /** Идентификатор пользователя (резолвится в реальные контакты) */
    USER_ID,

    /** Email-адрес */
    EMAIL,

    /** Номер телефона */
    PHONE
}

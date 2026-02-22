package com.example.notification.common.contact.impl;

import com.example.notification.common.contact.Contact;
import com.example.notification.common.contact.ContactType;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Телефонный контакт для прямой отправки (SMS, звонок и т.д.).
 * Нормализация номера (E.164) — задача adapter/validator, не common-модели.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneContact implements Contact, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Номер телефона */
    private String phoneNumber;

    @Override
    public ContactType getType() {
        return ContactType.PHONE;
    }
}

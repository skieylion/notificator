package com.example.notification.common.event;

import com.example.notification.common.contact.Contact;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Получатель уведомления.
 * Содержит список контактов для доставки.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipient implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Локаль получателя (например en, ru-RU) для локализации шаблона. Может быть null.
     */
    private String locale;

    /**
     * Контакты получателя.
     * Может содержать UserIdContact (для резолва) или прямые контакты (Email, Phone).
     */
    private List<Contact> contacts;
}

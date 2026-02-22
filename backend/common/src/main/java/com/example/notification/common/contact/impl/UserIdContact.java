package com.example.notification.common.contact.impl;

import com.example.notification.common.contact.Contact;
import com.example.notification.common.contact.ContactType;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Контакт по идентификатору пользователя.
 * Dispatcher резолвит userId в реальные контакты (email, phone и т.д.)
 * из профиля пользователя.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserIdContact implements Contact, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Идентификатор пользователя в системе */
    private String userId;

    @Override
    public ContactType getType() {
        return ContactType.USER_ID;
    }
}

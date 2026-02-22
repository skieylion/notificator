package com.example.notification.common.contact.impl;

import com.example.notification.common.contact.Contact;
import com.example.notification.common.contact.ContactType;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Email-контакт для прямой отправки.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailContact implements Contact, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Email-адрес */
    private String email;

    @Override
    public ContactType getType() {
        return ContactType.EMAIL;
    }
}

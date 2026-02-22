package com.example.notification.ingest.infrastructure.messaging.mapper;

import com.example.notification.common.contact.Contact;
import com.example.notification.common.contact.impl.*;
import com.example.notification.common.event.*;
import com.example.notification.common.sender.Sender;
import com.example.notification.common.sender.impl.*;
import com.example.notification.ingest.infrastructure.messaging.dto.*;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "jakarta")
public interface NotificationEventMapper {

    default NotificationEventPayload toPayload(NotificationEvent event) {
        if (event == null) {
            return null;
        }

        return NotificationEventPayload.builder()
                .id(event.getId())
                .idempotencyKey(event.getIdempotencyKey())
                .code(event.getCode())
                .params(event.getParams())
                .channels(event.getChannels())
                .recipients(toRecipientPayloads(event.getRecipients()))
                .sender(toSenderPayload(event.getSender()))
                .build();
    }

    default List<RecipientPayload> toRecipientPayloads(List<Recipient> recipients) {
        if (recipients == null) {
            return null;
        }
        return recipients.stream()
                .map(this::toRecipientPayload)
                .collect(Collectors.toList());
    }

    default RecipientPayload toRecipientPayload(Recipient recipient) {
        if (recipient == null || recipient.getContacts() == null) {
            return null;
        }

        List<ContactPayload> contactPayloads = recipient.getContacts().stream()
                .map(this::toContactPayload)
                .collect(Collectors.toList());

        return RecipientPayload.builder()
                .locale(recipient.getLocale())
                .contacts(contactPayloads)
                .build();
    }

    default ContactPayload toContactPayload(Contact contact) {
        if (contact == null) {
            return null;
        }

        if (contact instanceof UserIdContact userContact) {
            return ContactPayload.UserIdContactPayload.builder()
                    .userId(userContact.getUserId())
                    .build();
        }
        if (contact instanceof EmailContact emailContact) {
            return ContactPayload.EmailContactPayload.builder()
                    .email(emailContact.getEmail())
                    .build();
        }
        if (contact instanceof PhoneContact phoneContact) {
            return ContactPayload.PhoneContactPayload.builder()
                    .phoneNumber(phoneContact.getPhoneNumber())
                    .build();
        }

        throw new IllegalArgumentException("Unknown contact type: " + contact.getClass());
    }

    default SenderPayload toSenderPayload(Sender sender) {
        if (sender == null) {
            return null;
        }

        if (sender instanceof UserSender userSender) {
            return SenderPayload.UserSenderPayload.builder()
                    .userId(userSender.getUserId())
                    .build();
        }
        if (sender instanceof SystemSender systemSender) {
            return SenderPayload.SystemSenderPayload.builder()
                    .systemId(systemSender.getSystemId())
                    .build();
        }

        throw new IllegalArgumentException("Unknown sender type: " + sender.getClass());
    }
}

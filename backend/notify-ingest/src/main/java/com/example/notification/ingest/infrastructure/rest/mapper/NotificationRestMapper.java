package com.example.notification.ingest.infrastructure.rest.mapper;

import com.example.notification.common.contact.Contact;
import com.example.notification.common.contact.impl.*;
import com.example.notification.common.event.*;
import com.example.notification.common.sender.Sender;
import com.example.notification.common.sender.impl.*;
import com.example.notification.ingest.domain.IngestResult;
import com.example.notification.ingest.infrastructure.rest.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface NotificationRestMapper {

    @Mapping(target = "id", expression = "java(generateId())")
    @Mapping(target = "idempotencyKey", source = "idempotencyKey")
    @Mapping(target = "sender", source = "dto.sender")
    @Mapping(target = "code", source = "dto.code")
    @Mapping(target = "params", source = "dto.params")
    @Mapping(target = "channels", source = "dto.channels")
    @Mapping(target = "recipients", source = "dto.recipients")
    NotificationEvent toEvent(NotificationRequestDto dto, String idempotencyKey);

    default Recipient toRecipient(RecipientDto dto) {
        if (dto == null) {
            return null;
        }
        List<Contact> contacts = dto.getContacts().stream()
                .map(this::toContact)
                .collect(Collectors.toList());

        return Recipient.builder()
                .locale(dto.getLocale())
                .contacts(contacts)
                .build();
    }

    default Contact toContact(ContactDto dto) {
        if (dto == null) {
            return null;
        }

        if (dto instanceof ContactDto.UserIdContactDto userDto) {
            return UserIdContact.builder()
                    .userId(userDto.getUserId())
                    .build();
        }
        if (dto instanceof ContactDto.EmailContactDto emailDto) {
            return EmailContact.builder()
                    .email(emailDto.getEmail())
                    .build();
        }
        if (dto instanceof ContactDto.PhoneContactDto phoneDto) {
            return PhoneContact.builder()
                    .phoneNumber(phoneDto.getPhoneNumber())
                    .build();
        }

        throw new IllegalArgumentException("Unknown contact type: " + dto.getClass());
    }

    default Sender toSender(SenderDto dto) {
        if (dto == null) {
            return null;
        }

        if (dto instanceof SenderDto.UserSenderDto userDto) {
            return UserSender.builder()
                    .userId(userDto.getUserId())
                    .build();
        }
        if (dto instanceof SenderDto.SystemSenderDto systemDto) {
            return SystemSender.builder()
                    .systemId(systemDto.getSystemId())
                    .build();
        }

        throw new IllegalArgumentException("Unknown sender type: " + dto.getClass());
    }

    default String generateId() {
        return UUID.randomUUID().toString();
    }

    default NotificationResponseDto toResponse(String id, IngestResult result) {
        return NotificationResponseDto.builder()
                .id(id)
                .status(result.name().toLowerCase())
                .build();
    }
}

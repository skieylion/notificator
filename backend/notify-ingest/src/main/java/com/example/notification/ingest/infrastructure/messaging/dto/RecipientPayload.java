package com.example.notification.ingest.infrastructure.messaging.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipientPayload {
    private String locale;
    private List<ContactPayload> contacts;
}

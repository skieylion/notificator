package com.example.notification.ingest.infrastructure.messaging.dto;

import lombok.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEventPayload {
    private String id;
    private String idempotencyKey;
    private String code;
    private Map<String, String> params;
    private List<String> channels;
    private List<RecipientPayload> recipients;
    private SenderPayload sender;
}

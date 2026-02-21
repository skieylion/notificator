package com.example.notification.inbox.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationItemDto {
    private Long id;
    private String notificationId;
    private String userId;
    private String status;
    private String channel;
    private Instant createdAt;
    private Instant readAt;
}

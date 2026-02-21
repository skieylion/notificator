package com.example.notification.inbox.domain;

import java.time.Instant;

/**
 * Read model: a notification record in a user's inbox.
 */
public record NotificationRecord(
    Long id,
    String notificationId,
    String userId,
    String status,
    String channel,
    Instant createdAt,
    Instant readAt
) {}

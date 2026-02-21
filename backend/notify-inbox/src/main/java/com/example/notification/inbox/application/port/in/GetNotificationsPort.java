package com.example.notification.inbox.application.port.in;

import com.example.notification.inbox.domain.NotificationRecord;

import java.util.List;

public interface GetNotificationsPort {
    List<NotificationRecord> list(String userId);
}

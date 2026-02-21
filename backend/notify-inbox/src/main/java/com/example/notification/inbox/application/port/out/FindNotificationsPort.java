package com.example.notification.inbox.application.port.out;

import com.example.notification.inbox.domain.NotificationRecord;

import java.util.List;

public interface FindNotificationsPort {
    List<NotificationRecord> findByUserId(String userId);

    long countUnreadByUserId(String userId);
}

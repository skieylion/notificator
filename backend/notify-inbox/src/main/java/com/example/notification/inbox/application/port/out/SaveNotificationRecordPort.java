package com.example.notification.inbox.application.port.out;

import com.example.notification.inbox.domain.NotificationRecord;

public interface SaveNotificationRecordPort {
    void save(NotificationRecord record);
}

package com.example.notification.inbox.application;

import com.example.notification.inbox.application.port.in.GetNotificationsPort;
import com.example.notification.inbox.application.port.out.FindNotificationsPort;
import com.example.notification.inbox.domain.NotificationRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetNotificationsUseCase implements GetNotificationsPort {

    private final FindNotificationsPort findNotificationsPort;

    public GetNotificationsUseCase(FindNotificationsPort findNotificationsPort) {
        this.findNotificationsPort = findNotificationsPort;
    }

    @Override
    public List<NotificationRecord> list(String userId) {
        return findNotificationsPort.findByUserId(userId);
    }
}

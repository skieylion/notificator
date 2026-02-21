package com.example.notification.inbox.application;

import com.example.notification.inbox.application.port.in.GetUnreadPort;
import com.example.notification.inbox.application.port.out.FindNotificationsPort;
import org.springframework.stereotype.Service;

@Service
public class GetUnreadUseCase implements GetUnreadPort {

    private final FindNotificationsPort findNotificationsPort;

    public GetUnreadUseCase(FindNotificationsPort findNotificationsPort) {
        this.findNotificationsPort = findNotificationsPort;
    }

    @Override
    public long count(String userId) {
        return findNotificationsPort.countUnreadByUserId(userId);
    }
}

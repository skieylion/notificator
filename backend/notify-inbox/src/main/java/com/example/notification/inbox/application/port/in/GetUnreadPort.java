package com.example.notification.inbox.application.port.in;

public interface GetUnreadPort {
    long count(String userId);
}

package com.example.notification.inbox.application.port.in;

import com.example.notification.common.kafka.DeliveryEventPayload;

public interface HandleDeliveryEventPort {
    void handle(DeliveryEventPayload payload);
}

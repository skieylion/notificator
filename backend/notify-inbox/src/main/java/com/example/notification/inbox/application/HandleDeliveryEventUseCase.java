package com.example.notification.inbox.application;

import com.example.notification.common.kafka.DeliveryEventPayload;
import com.example.notification.inbox.application.port.in.HandleDeliveryEventPort;
import com.example.notification.inbox.application.port.out.SaveNotificationRecordPort;
import com.example.notification.inbox.domain.NotificationRecord;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class HandleDeliveryEventUseCase implements HandleDeliveryEventPort {

    private final SaveNotificationRecordPort saveNotificationRecordPort;

    public HandleDeliveryEventUseCase(SaveNotificationRecordPort saveNotificationRecordPort) {
        this.saveNotificationRecordPort = saveNotificationRecordPort;
    }

    @Override
    public void handle(DeliveryEventPayload payload) {
        Instant createdAt = payload.getTimestamp() != null
            ? Instant.parse(payload.getTimestamp())
            : Instant.now();
        NotificationRecord record = new NotificationRecord(
            null,
            payload.getNotificationId(),
            payload.getRecipientId(),
            payload.getStatus(),
            payload.getChannel(),
            createdAt,
            null
        );
        saveNotificationRecordPort.save(record);
    }
}

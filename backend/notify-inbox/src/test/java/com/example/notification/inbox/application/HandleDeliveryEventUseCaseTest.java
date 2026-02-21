package com.example.notification.inbox.application;

import com.example.notification.common.kafka.DeliveryEventPayload;
import com.example.notification.inbox.application.port.out.SaveNotificationRecordPort;
import com.example.notification.inbox.domain.NotificationRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HandleDeliveryEventUseCaseTest {

    @Mock
    private SaveNotificationRecordPort saveNotificationRecordPort;

    private HandleDeliveryEventUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new HandleDeliveryEventUseCase(saveNotificationRecordPort);
    }

    @Test
    void handle_savesRecordWithPayloadData() {
        DeliveryEventPayload payload = new DeliveryEventPayload(
            "notif-123",
            "DELIVERED",
            "EMAIL",
            "2025-02-22T12:00:00Z",
            "user-1"
        );

        useCase.handle(payload);

        ArgumentCaptor<NotificationRecord> captor = ArgumentCaptor.forClass(NotificationRecord.class);
        verify(saveNotificationRecordPort).save(captor.capture());
        NotificationRecord saved = captor.getValue();
        assertThat(saved.notificationId()).isEqualTo("notif-123");
        assertThat(saved.userId()).isEqualTo("user-1");
        assertThat(saved.status()).isEqualTo("DELIVERED");
        assertThat(saved.channel()).isEqualTo("EMAIL");
        assertThat(saved.readAt()).isNull();
        assertThat(saved.createdAt()).isNotNull();
    }

    @Test
    void handle_whenTimestampNull_usesNow() {
        DeliveryEventPayload payload = new DeliveryEventPayload("n1", "FAILED", "SMS", null, "u2");

        useCase.handle(payload);

        ArgumentCaptor<NotificationRecord> captor = ArgumentCaptor.forClass(NotificationRecord.class);
        verify(saveNotificationRecordPort).save(captor.capture());
        assertThat(captor.getValue().createdAt()).isNotNull();
    }
}

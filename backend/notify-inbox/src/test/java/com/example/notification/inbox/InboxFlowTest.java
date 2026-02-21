package com.example.notification.inbox;

import com.example.notification.common.kafka.DeliveryEventPayload;
import com.example.notification.inbox.application.port.in.GetNotificationsPort;
import com.example.notification.inbox.application.port.in.GetUnreadPort;
import com.example.notification.inbox.application.port.in.HandleDeliveryEventPort;
import com.example.notification.inbox.domain.NotificationRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests the flow inside notify-inbox: handle delivery event -> save to read model -> list and unread count.
 * Kafka is not used; we call ports directly (same flow as would be triggered by Kafka consumer).
 */
@SpringBootTest
class InboxFlowTest {

    @Autowired
    private HandleDeliveryEventPort handleDeliveryEventPort;

    @Autowired
    private GetNotificationsPort getNotificationsPort;

    @Autowired
    private GetUnreadPort getUnreadPort;

    @Test
    void flow_handleEventThenListAndUnread() {
        String userId = "user-flow-1";
        DeliveryEventPayload payload = new DeliveryEventPayload(
            "notif-flow-1",
            "DELIVERED",
            "EMAIL",
            "2025-02-22T12:00:00Z",
            userId
        );

        handleDeliveryEventPort.handle(payload);

        List<NotificationRecord> list = getNotificationsPort.list(userId);
        assertThat(list).hasSize(1);
        assertThat(list.get(0).notificationId()).isEqualTo("notif-flow-1");
        assertThat(list.get(0).userId()).isEqualTo(userId);
        assertThat(list.get(0).status()).isEqualTo("DELIVERED");

        long unread = getUnreadPort.count(userId);
        assertThat(unread).isEqualTo(1L);
    }

    @Test
    void flow_multipleUsers_separateLists() {
        handleDeliveryEventPort.handle(new DeliveryEventPayload(
            "n-a", "DELIVERED", "EMAIL", "2025-02-22T12:00:00Z", "user-A"));
        handleDeliveryEventPort.handle(new DeliveryEventPayload(
            "n-b", "DELIVERED", "SMS", "2025-02-22T12:01:00Z", "user-B"));

        List<NotificationRecord> listA = getNotificationsPort.list("user-A");
        assertThat(listA).hasSize(1);
        assertThat(listA.get(0).notificationId()).isEqualTo("n-a");

        List<NotificationRecord> listB = getNotificationsPort.list("user-B");
        assertThat(listB).hasSize(1);
        assertThat(listB.get(0).notificationId()).isEqualTo("n-b");

        assertThat(getUnreadPort.count("user-A")).isEqualTo(1L);
        assertThat(getUnreadPort.count("user-B")).isEqualTo(1L);
    }
}

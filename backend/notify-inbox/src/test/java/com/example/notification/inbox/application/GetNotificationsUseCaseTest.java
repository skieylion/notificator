package com.example.notification.inbox.application;

import com.example.notification.inbox.application.port.out.FindNotificationsPort;
import com.example.notification.inbox.domain.NotificationRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetNotificationsUseCaseTest {

    @Mock
    private FindNotificationsPort findNotificationsPort;

    private GetNotificationsUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetNotificationsUseCase(findNotificationsPort);
    }

    @Test
    void list_returnsRecordsFromPort() {
        String userId = "user-1";
        List<NotificationRecord> records = List.of(
            new NotificationRecord(1L, "n1", userId, "DELIVERED", "EMAIL", Instant.now(), null)
        );
        when(findNotificationsPort.findByUserId(userId)).thenReturn(records);

        List<NotificationRecord> result = useCase.list(userId);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).notificationId()).isEqualTo("n1");
        assertThat(result.get(0).userId()).isEqualTo(userId);
    }

    @Test
    void list_whenEmpty_returnsEmptyList() {
        when(findNotificationsPort.findByUserId("user-2")).thenReturn(List.of());

        assertThat(useCase.list("user-2")).isEmpty();
    }
}

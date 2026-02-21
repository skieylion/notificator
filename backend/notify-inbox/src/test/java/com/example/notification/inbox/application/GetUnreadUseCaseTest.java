package com.example.notification.inbox.application;

import com.example.notification.inbox.application.port.out.FindNotificationsPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUnreadUseCaseTest {

    @Mock
    private FindNotificationsPort findNotificationsPort;

    private GetUnreadUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetUnreadUseCase(findNotificationsPort);
    }

    @Test
    void count_returnsUnreadCountFromPort() {
        when(findNotificationsPort.countUnreadByUserId("user-1")).thenReturn(3L);

        assertThat(useCase.count("user-1")).isEqualTo(3L);
    }

    @Test
    void count_whenZero_returnsZero() {
        when(findNotificationsPort.countUnreadByUserId("user-2")).thenReturn(0L);

        assertThat(useCase.count("user-2")).isEqualTo(0L);
    }
}

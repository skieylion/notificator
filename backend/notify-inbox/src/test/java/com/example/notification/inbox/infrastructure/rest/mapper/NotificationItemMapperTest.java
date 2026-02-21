package com.example.notification.inbox.infrastructure.rest.mapper;

import com.example.notification.inbox.domain.NotificationRecord;
import com.example.notification.inbox.infrastructure.rest.dto.NotificationItemDto;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationItemMapperTest {

    private final NotificationItemMapper mapper = new NotificationItemMapperImpl();

    @Test
    void toDto_mapsRecordToDto() {
        Instant now = Instant.now();
        NotificationRecord record = new NotificationRecord(
            1L, "notif-1", "user-1", "DELIVERED", "EMAIL", now, null
        );

        NotificationItemDto dto = mapper.toDto(record);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getNotificationId()).isEqualTo("notif-1");
        assertThat(dto.getUserId()).isEqualTo("user-1");
        assertThat(dto.getStatus()).isEqualTo("DELIVERED");
        assertThat(dto.getChannel()).isEqualTo("EMAIL");
        assertThat(dto.getCreatedAt()).isEqualTo(now);
        assertThat(dto.getReadAt()).isNull();
    }
}

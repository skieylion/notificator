package com.example.notification.inbox.infrastructure.rest.mapper;

import com.example.notification.inbox.domain.NotificationRecord;
import com.example.notification.inbox.infrastructure.rest.dto.NotificationItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationItemMapper {
    NotificationItemDto toDto(NotificationRecord record);
}

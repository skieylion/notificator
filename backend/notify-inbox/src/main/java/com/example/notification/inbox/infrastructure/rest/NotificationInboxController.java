package com.example.notification.inbox.infrastructure.rest;

import com.example.notification.inbox.application.port.in.GetNotificationsPort;
import com.example.notification.inbox.application.port.in.GetUnreadPort;
import com.example.notification.inbox.infrastructure.rest.dto.NotificationItemDto;
import com.example.notification.inbox.infrastructure.rest.mapper.NotificationItemMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
public class NotificationInboxController {

    private final GetNotificationsPort getNotificationsPort;
    private final GetUnreadPort getUnreadPort;
    private final NotificationItemMapper mapper;

    public NotificationInboxController(GetNotificationsPort getNotificationsPort,
                                      GetUnreadPort getUnreadPort,
                                      NotificationItemMapper mapper) {
        this.getNotificationsPort = getNotificationsPort;
        this.getUnreadPort = getUnreadPort;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<NotificationItemDto>> list(@RequestHeader("X-User-Id") String userId) {
        List<NotificationItemDto> list = getNotificationsPort.list(userId).stream()
            .map(mapper::toDto)
            .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/unread")
    public ResponseEntity<Map<String, Long>> unread(@RequestHeader("X-User-Id") String userId) {
        long count = getUnreadPort.count(userId);
        return ResponseEntity.ok(Map.of("count", count));
    }
}

package com.example.notification.inbox.infrastructure.persistence;

import com.example.notification.inbox.application.port.out.FindNotificationsPort;
import com.example.notification.inbox.application.port.out.SaveNotificationRecordPort;
import com.example.notification.inbox.domain.NotificationRecord;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NotificationRecordPersistenceAdapter implements SaveNotificationRecordPort, FindNotificationsPort {

    private final NotificationRecordRepository repository;

    public NotificationRecordPersistenceAdapter(NotificationRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(NotificationRecord record) {
        Optional<NotificationRecordEntity> existing = repository.findByNotificationId(record.notificationId());
        if (existing.isPresent()) {
            NotificationRecordEntity e = existing.get();
            e.setStatus(record.status());
            e.setReadAt(record.readAt());
            repository.save(e);
        } else {
            NotificationRecordEntity entity = toEntity(record);
            repository.save(entity);
        }
    }

    private NotificationRecordEntity toEntity(NotificationRecord record) {
        NotificationRecordEntity entity = new NotificationRecordEntity();
        entity.setId(record.id());
        entity.setNotificationId(record.notificationId());
        entity.setUserId(record.userId());
        entity.setStatus(record.status());
        entity.setChannel(record.channel());
        entity.setCreatedAt(record.createdAt());
        entity.setReadAt(record.readAt());
        return entity;
    }

    @Override
    public List<NotificationRecord> findByUserId(String userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId).stream()
            .map(this::toRecord)
            .toList();
    }

    @Override
    public long countUnreadByUserId(String userId) {
        return repository.countByUserIdAndReadAtIsNull(userId);
    }

    private NotificationRecord toRecord(NotificationRecordEntity entity) {
        return new NotificationRecord(
            entity.getId(),
            entity.getNotificationId(),
            entity.getUserId(),
            entity.getStatus(),
            entity.getChannel(),
            entity.getCreatedAt(),
            entity.getReadAt()
        );
    }
}

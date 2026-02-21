package com.example.notification.inbox.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.Optional;

public interface NotificationRecordRepository extends JpaRepository<NotificationRecordEntity, Long> {

    List<NotificationRecordEntity> findByUserIdOrderByCreatedAtDesc(String userId);

    long countByUserIdAndReadAtIsNull(String userId);

    Optional<NotificationRecordEntity> findByNotificationId(String notificationId);
}

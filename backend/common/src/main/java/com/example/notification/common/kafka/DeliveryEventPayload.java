package com.example.notification.common.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Event payload for delivery events (dispatcher → Kafka → inbox, audit, etc.).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryEventPayload {
    private String notificationId;
    private String status;
    private String channel;
    private String timestamp;
    private String recipientId;

    public DeliveryEventPayload() {
    }

    public DeliveryEventPayload(String notificationId, String status, String channel, String timestamp, String recipientId) {
        this.notificationId = notificationId;
        this.status = status;
        this.channel = channel;
        this.timestamp = timestamp;
        this.recipientId = recipientId;
    }

    public String getNotificationId() { return notificationId; }
    public void setNotificationId(String notificationId) { this.notificationId = notificationId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public String getRecipientId() { return recipientId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }
}

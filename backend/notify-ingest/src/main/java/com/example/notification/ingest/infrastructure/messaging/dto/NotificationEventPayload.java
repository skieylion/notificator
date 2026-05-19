package com.example.notification.ingest.infrastructure.messaging.dto;

import java.util.List;
import java.util.Map;

public class NotificationEventPayload {
    private String id;
    private String idempotencyKey;
    private String code;
    private Map<String, String> params;
    private List<String> channels;
    private List<RecipientPayload> recipients;
    private SenderPayload sender;

    public NotificationEventPayload() {}

    public NotificationEventPayload(String id, String idempotencyKey, String code,
                                     Map<String, String> params, List<String> channels,
                                     List<RecipientPayload> recipients, SenderPayload sender) {
        this.id = id;
        this.idempotencyKey = idempotencyKey;
        this.code = code;
        this.params = params;
        this.channels = channels;
        this.recipients = recipients;
        this.sender = sender;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdempotencyKey() { return idempotencyKey; }
    public void setIdempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Map<String, String> getParams() { return params; }
    public void setParams(Map<String, String> params) { this.params = params; }

    public List<String> getChannels() { return channels; }
    public void setChannels(List<String> channels) { this.channels = channels; }

    public List<RecipientPayload> getRecipients() { return recipients; }
    public void setRecipients(List<RecipientPayload> recipients) { this.recipients = recipients; }

    public SenderPayload getSender() { return sender; }
    public void setSender(SenderPayload sender) { this.sender = sender; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id;
        private String idempotencyKey;
        private String code;
        private Map<String, String> params;
        private List<String> channels;
        private List<RecipientPayload> recipients;
        private SenderPayload sender;

        public Builder id(String id) { this.id = id; return this; }
        public Builder idempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey; return this; }
        public Builder code(String code) { this.code = code; return this; }
        public Builder params(Map<String, String> params) { this.params = params; return this; }
        public Builder channels(List<String> channels) { this.channels = channels; return this; }
        public Builder recipients(List<RecipientPayload> recipients) { this.recipients = recipients; return this; }
        public Builder sender(SenderPayload sender) { this.sender = sender; return this; }

        public NotificationEventPayload build() {
            return new NotificationEventPayload(id, idempotencyKey, code, params, channels, recipients, sender);
        }
    }
}

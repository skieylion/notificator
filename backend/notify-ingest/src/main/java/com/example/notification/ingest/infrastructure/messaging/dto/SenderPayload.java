package com.example.notification.ingest.infrastructure.messaging.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SenderPayload.UserSenderPayload.class, name = "USER"),
    @JsonSubTypes.Type(value = SenderPayload.SystemSenderPayload.class, name = "SYSTEM")
})
public abstract class SenderPayload {

    public abstract String getType();
    public abstract String getId();

    public static class UserSenderPayload extends SenderPayload {
        private final String type = "USER";
        private String userId;

        public UserSenderPayload() {}
        public UserSenderPayload(String userId) { this.userId = userId; }

        @Override public String getType() { return type; }
        @Override public String getId() { return userId; }
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String userId;
            public Builder userId(String userId) { this.userId = userId; return this; }
            public UserSenderPayload build() { return new UserSenderPayload(userId); }
        }
    }

    public static class SystemSenderPayload extends SenderPayload {
        private final String type = "SYSTEM";
        private String systemId;

        public SystemSenderPayload() {}
        public SystemSenderPayload(String systemId) { this.systemId = systemId; }

        @Override public String getType() { return type; }
        @Override public String getId() { return systemId; }
        public String getSystemId() { return systemId; }
        public void setSystemId(String systemId) { this.systemId = systemId; }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String systemId;
            public Builder systemId(String systemId) { this.systemId = systemId; return this; }
            public SystemSenderPayload build() { return new SystemSenderPayload(systemId); }
        }
    }
}

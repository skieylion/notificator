package com.example.notification.ingest.infrastructure.messaging.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SenderPayload.UserSenderPayload.class, name = "USER"),
    @JsonSubTypes.Type(value = SenderPayload.SystemSenderPayload.class, name = "SYSTEM")
})
public abstract class SenderPayload {

    public abstract String getType();
    public abstract String getId();

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UserSenderPayload extends SenderPayload {
        private final String type = "USER";
        private String userId;
        @Override public String getType() { return type; }
        @Override public String getId() { return userId; }
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class SystemSenderPayload extends SenderPayload {
        private final String type = "SYSTEM";
        private String systemId;
        @Override public String getType() { return type; }
        @Override public String getId() { return systemId; }
    }
}

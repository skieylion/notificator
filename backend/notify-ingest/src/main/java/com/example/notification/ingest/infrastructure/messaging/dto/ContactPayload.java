package com.example.notification.ingest.infrastructure.messaging.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ContactPayload.UserIdContactPayload.class, name = "USER_ID"),
    @JsonSubTypes.Type(value = ContactPayload.EmailContactPayload.class, name = "EMAIL"),
    @JsonSubTypes.Type(value = ContactPayload.PhoneContactPayload.class, name = "PHONE")
})
public abstract class ContactPayload {

    public abstract String getType();

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UserIdContactPayload extends ContactPayload {
        private final String type = "USER_ID";
        private String userId;
        @Override public String getType() { return type; }
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class EmailContactPayload extends ContactPayload {
        private final String type = "EMAIL";
        private String email;
        @Override public String getType() { return type; }
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PhoneContactPayload extends ContactPayload {
        private final String type = "PHONE";
        private String phoneNumber;
        @Override public String getType() { return type; }
    }
}

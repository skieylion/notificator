package com.example.notification.ingest.infrastructure.messaging.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ContactPayload.UserIdContactPayload.class, name = "USER_ID"),
    @JsonSubTypes.Type(value = ContactPayload.EmailContactPayload.class, name = "EMAIL"),
    @JsonSubTypes.Type(value = ContactPayload.PhoneContactPayload.class, name = "PHONE")
})
public abstract class ContactPayload {

    public abstract String getType();

    public static class UserIdContactPayload extends ContactPayload {
        private final String type = "USER_ID";
        private String userId;

        public UserIdContactPayload() {}
        public UserIdContactPayload(String userId) { this.userId = userId; }

        @Override public String getType() { return type; }
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String userId;
            public Builder userId(String userId) { this.userId = userId; return this; }
            public UserIdContactPayload build() { return new UserIdContactPayload(userId); }
        }
    }

    public static class EmailContactPayload extends ContactPayload {
        private final String type = "EMAIL";
        private String email;

        public EmailContactPayload() {}
        public EmailContactPayload(String email) { this.email = email; }

        @Override public String getType() { return type; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String email;
            public Builder email(String email) { this.email = email; return this; }
            public EmailContactPayload build() { return new EmailContactPayload(email); }
        }
    }

    public static class PhoneContactPayload extends ContactPayload {
        private final String type = "PHONE";
        private String phoneNumber;

        public PhoneContactPayload() {}
        public PhoneContactPayload(String phoneNumber) { this.phoneNumber = phoneNumber; }

        @Override public String getType() { return type; }
        public String getPhoneNumber() { return phoneNumber; }
        public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String phoneNumber;
            public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
            public PhoneContactPayload build() { return new PhoneContactPayload(phoneNumber); }
        }
    }
}

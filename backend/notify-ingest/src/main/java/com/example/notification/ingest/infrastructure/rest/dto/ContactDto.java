package com.example.notification.ingest.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Базовый DTO контакта с полиморфной десериализацией.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ContactDto.UserIdContactDto.class, name = "USER_ID"),
    @JsonSubTypes.Type(value = ContactDto.EmailContactDto.class, name = "EMAIL"),
    @JsonSubTypes.Type(value = ContactDto.PhoneContactDto.class, name = "PHONE")
})
@Schema(description = "Контакт получателя")
public abstract class ContactDto {

    public abstract String getType();

    @Schema(description = "Контакт по ID пользователя")
    public static class UserIdContactDto extends ContactDto {

        @Schema(description = "Тип контакта", example = "USER_ID", requiredMode = Schema.RequiredMode.REQUIRED)
        private final String type = "USER_ID";

        @Schema(description = "ID пользователя", example = "user-123", requiredMode = Schema.RequiredMode.REQUIRED)
        private String userId;

        public UserIdContactDto() {}

        public UserIdContactDto(String userId) {
            this.userId = userId;
        }

        @Override
        public String getType() {
            return type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public static UserIdContactDtoBuilder builder() {
            return new UserIdContactDtoBuilder();
        }

        public static class UserIdContactDtoBuilder {
            private String userId;

            public UserIdContactDtoBuilder userId(String userId) {
                this.userId = userId;
                return this;
            }

            public UserIdContactDto build() {
                return new UserIdContactDto(userId);
            }
        }
    }

    @Schema(description = "Email-контакт")
    public static class EmailContactDto extends ContactDto {

        @Schema(description = "Тип контакта", example = "EMAIL", requiredMode = Schema.RequiredMode.REQUIRED)
        private final String type = "EMAIL";

        @Schema(description = "Email-адрес", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
        private String email;

        public EmailContactDto() {}

        public EmailContactDto(String email) {
            this.email = email;
        }

        @Override
        public String getType() {
            return type;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public static EmailContactDtoBuilder builder() {
            return new EmailContactDtoBuilder();
        }

        public static class EmailContactDtoBuilder {
            private String email;

            public EmailContactDtoBuilder email(String email) {
                this.email = email;
                return this;
            }

            public EmailContactDto build() {
                return new EmailContactDto(email);
            }
        }
    }

    @Schema(description = "Телефонный контакт")
    public static class PhoneContactDto extends ContactDto {

        @Schema(description = "Тип контакта", example = "PHONE", requiredMode = Schema.RequiredMode.REQUIRED)
        private final String type = "PHONE";

        @Schema(description = "Номер телефона", example = "+79001234567", requiredMode = Schema.RequiredMode.REQUIRED)
        private String phoneNumber;

        public PhoneContactDto() {}

        public PhoneContactDto(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String getType() {
            return type;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public static PhoneContactDtoBuilder builder() {
            return new PhoneContactDtoBuilder();
        }

        public static class PhoneContactDtoBuilder {
            private String phoneNumber;

            public PhoneContactDtoBuilder phoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
                return this;
            }

            public PhoneContactDto build() {
                return new PhoneContactDto(phoneNumber);
            }
        }
    }
}

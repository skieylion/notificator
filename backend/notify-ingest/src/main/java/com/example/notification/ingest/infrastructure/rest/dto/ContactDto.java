package com.example.notification.ingest.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.DiscriminatorMapping;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

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
@Schema(
    description = "Контакт получателя",
    discriminatorProperty = "type",
    discriminatorMapping = {
        @DiscriminatorMapping(value = "USER_ID", schema = ContactDto.UserIdContactDto.class),
        @DiscriminatorMapping(value = "EMAIL", schema = ContactDto.EmailContactDto.class),
        @DiscriminatorMapping(value = "PHONE", schema = ContactDto.PhoneContactDto.class)
    }
)
public abstract class ContactDto {

    public abstract String getType();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Контакт по ID пользователя")
    public static class UserIdContactDto extends ContactDto {

        @Schema(description = "Тип контакта", example = "USER_ID", required = true)
        private final String type = "USER_ID";

        @Schema(description = "ID пользователя", example = "user-123", required = true)
        private String userId;

        @Override
        public String getType() {
            return type;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Email-контакт")
    public static class EmailContactDto extends ContactDto {

        @Schema(description = "Тип контакта", example = "EMAIL", required = true)
        private final String type = "EMAIL";

        @Schema(description = "Email-адрес", example = "user@example.com", required = true)
        private String email;

        @Override
        public String getType() {
            return type;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Телефонный контакт")
    public static class PhoneContactDto extends ContactDto {

        @Schema(description = "Тип контакта", example = "PHONE", required = true)
        private final String type = "PHONE";

        @Schema(description = "Номер телефона", example = "+79001234567", required = true)
        private String phoneNumber;

        @Override
        public String getType() {
            return type;
        }
    }
}

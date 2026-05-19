package com.example.notification.ingest.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Контакт по ID пользователя")
    public static class UserIdContactDto extends ContactDto {
        @Schema(hidden = true)
        private final String type = "USER_ID";

        @Schema(description = "ID пользователя", example = "user-123", requiredMode = Schema.RequiredMode.REQUIRED)
        private String userId;

        @Override
        public String getType() { return type; }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Email-контакт")
    public static class EmailContactDto extends ContactDto {
        @Schema(hidden = true)
        private final String type = "EMAIL";

        @Schema(description = "Email-адрес", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
        private String email;

        @Override
        public String getType() { return type; }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Телефонный контакт")
    public static class PhoneContactDto extends ContactDto {
        @Schema(hidden = true)
        private final String type = "PHONE";

        @Schema(description = "Номер телефона", example = "+79001234567", requiredMode = Schema.RequiredMode.REQUIRED)
        private String phoneNumber;

        @Override
        public String getType() { return type; }
    }
}

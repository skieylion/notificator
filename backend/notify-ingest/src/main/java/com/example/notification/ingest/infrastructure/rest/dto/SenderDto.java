package com.example.notification.ingest.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.DiscriminatorMapping;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO отправителя с полиморфной десериализацией.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SenderDto.UserSenderDto.class, name = "USER"),
    @JsonSubTypes.Type(value = SenderDto.SystemSenderDto.class, name = "SYSTEM")
})
@Schema(
    description = "Инициатор отправки",
    discriminatorProperty = "type",
    discriminatorMapping = {
        @DiscriminatorMapping(value = "USER", schema = SenderDto.UserSenderDto.class),
        @DiscriminatorMapping(value = "SYSTEM", schema = SenderDto.SystemSenderDto.class)
    }
)
public abstract class SenderDto {

    public abstract String getType();
    public abstract String getId();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Отправитель — пользователь")
    public static class UserSenderDto extends SenderDto {

        @Schema(description = "Тип отправителя", example = "USER", required = true)
        private final String type = "USER";

        @Schema(description = "ID пользователя", example = "admin-1", required = true)
        private String userId;

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getId() {
            return userId;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Отправитель — системный сервис")
    public static class SystemSenderDto extends SenderDto {

        @Schema(description = "Тип отправителя", example = "SYSTEM", required = true)
        private final String type = "SYSTEM";

        @Schema(description = "ID сервиса", example = "billing-service", required = true)
        private String systemId;

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getId() {
            return systemId;
        }
    }
}

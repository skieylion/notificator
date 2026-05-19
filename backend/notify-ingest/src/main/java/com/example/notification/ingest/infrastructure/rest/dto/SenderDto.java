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
    @JsonSubTypes.Type(value = SenderDto.UserSenderDto.class, name = "USER"),
    @JsonSubTypes.Type(value = SenderDto.SystemSenderDto.class, name = "SYSTEM")
})
@Schema(description = "Инициатор отправки")
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
        @Schema(hidden = true)
        private final String type = "USER";

        @Schema(description = "ID пользователя", example = "admin-1", requiredMode = Schema.RequiredMode.REQUIRED)
        private String userId;

        @Override
        public String getType() { return type; }

        @Override
        public String getId() { return userId; }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Отправитель — системный сервис")
    public static class SystemSenderDto extends SenderDto {
        @Schema(hidden = true)
        private final String type = "SYSTEM";

        @Schema(description = "ID сервиса", example = "billing-service", requiredMode = Schema.RequiredMode.REQUIRED)
        private String systemId;

        @Override
        public String getType() { return type; }

        @Override
        public String getId() { return systemId; }
    }
}

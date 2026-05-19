package com.example.notification.ingest.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "Инициатор отправки")
public abstract class SenderDto {

    public abstract String getType();
    public abstract String getId();

    @Schema(description = "Отправитель — пользователь")
    public static class UserSenderDto extends SenderDto {

        @Schema(description = "Тип отправителя", example = "USER", requiredMode = Schema.RequiredMode.REQUIRED)
        private final String type = "USER";

        @Schema(description = "ID пользователя", example = "admin-1", requiredMode = Schema.RequiredMode.REQUIRED)
        private String userId;

        public UserSenderDto() {}

        public UserSenderDto(String userId) {
            this.userId = userId;
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getId() {
            return userId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public static UserSenderDtoBuilder builder() {
            return new UserSenderDtoBuilder();
        }

        public static class UserSenderDtoBuilder {
            private String userId;

            public UserSenderDtoBuilder userId(String userId) {
                this.userId = userId;
                return this;
            }

            public UserSenderDto build() {
                return new UserSenderDto(userId);
            }
        }
    }

    @Schema(description = "Отправитель — системный сервис")
    public static class SystemSenderDto extends SenderDto {

        @Schema(description = "Тип отправителя", example = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED)
        private final String type = "SYSTEM";

        @Schema(description = "ID сервиса", example = "billing-service", requiredMode = Schema.RequiredMode.REQUIRED)
        private String systemId;

        public SystemSenderDto() {}

        public SystemSenderDto(String systemId) {
            this.systemId = systemId;
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getId() {
            return systemId;
        }

        public String getSystemId() {
            return systemId;
        }

        public void setSystemId(String systemId) {
            this.systemId = systemId;
        }

        public static SystemSenderDtoBuilder builder() {
            return new SystemSenderDtoBuilder();
        }

        public static class SystemSenderDtoBuilder {
            private String systemId;

            public SystemSenderDtoBuilder systemId(String systemId) {
                this.systemId = systemId;
                return this;
            }

            public SystemSenderDto build() {
                return new SystemSenderDto(systemId);
            }
        }
    }
}

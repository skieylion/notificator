package com.example.notification.ingest.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ после приёма уведомления")
public class NotificationResponseDto {

    @Schema(description = "Уникальный ID уведомления", example = "550e8400-e29b-41d4-a716-446655440000")
    private String id;

    @Schema(description = "Статус приёма", example = "accepted", allowableValues = {"accepted"})
    private String status;

    public NotificationResponseDto() {}

    public NotificationResponseDto(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static NotificationResponseDtoBuilder builder() {
        return new NotificationResponseDtoBuilder();
    }

    public static class NotificationResponseDtoBuilder {
        private String id;
        private String status;

        public NotificationResponseDtoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public NotificationResponseDtoBuilder status(String status) {
            this.status = status;
            return this;
        }

        public NotificationResponseDto build() {
            return new NotificationResponseDto(id, status);
        }
    }
}

package com.example.notification.ingest.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Ответ после приёма уведомления")
public class NotificationResponseDto {

    @Schema(description = "Уникальный ID уведомления", example = "550e8400-e29b-41d4-a716-446655440000")
    private String id;

    @Schema(description = "Статус приёма", example = "accepted", allowableValues = {"accepted"})
    private String status;
}

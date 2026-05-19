package com.example.notification.ingest.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Запрос на создание уведомления")
public class NotificationRequestDto {

    @NotBlank(message = "code is required")
    @Schema(description = "Код уведомления/шаблона",
            example = "welcome_email",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    @Schema(description = "Параметры для подстановки в шаблон",
            example = "{\"name\": \"John\"}")
    private Map<String, String> params;

    @Schema(description = "Каналы доставки (EMAIL, SMS, PUSH и т.д.). Опционально.")
    private List<String> channels;

    @NotEmpty(message = "recipients must contain at least one element")
    @Valid
    @Schema(description = "Список получателей (минимум один)",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private List<RecipientDto> recipients;

    @NotNull(message = "sender is required")
    @Valid
    @Schema(description = "Инициатор отправки",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private SenderDto sender;
}

package com.example.notification.ingest.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Получатель уведомления")
public class RecipientDto {

    @Schema(description = "Локаль получателя (например en, ru-RU)", example = "en")
    private String locale;

    @NotEmpty(message = "contacts must contain at least one element")
    @Valid
    @Schema(description = "Контакты получателя (минимум один)", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ContactDto> contacts;
}

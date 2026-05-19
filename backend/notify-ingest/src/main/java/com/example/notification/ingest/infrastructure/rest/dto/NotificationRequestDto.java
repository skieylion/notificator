package com.example.notification.ingest.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

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

    public NotificationRequestDto() {}

    public NotificationRequestDto(String code, Map<String, String> params, List<String> channels,
                                   List<RecipientDto> recipients, SenderDto sender) {
        this.code = code;
        this.params = params;
        this.channels = channels;
        this.recipients = recipients;
        this.sender = sender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }

    public List<RecipientDto> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<RecipientDto> recipients) {
        this.recipients = recipients;
    }

    public SenderDto getSender() {
        return sender;
    }

    public void setSender(SenderDto sender) {
        this.sender = sender;
    }

    public static NotificationRequestDtoBuilder builder() {
        return new NotificationRequestDtoBuilder();
    }

    public static class NotificationRequestDtoBuilder {
        private String code;
        private Map<String, String> params;
        private List<String> channels;
        private List<RecipientDto> recipients;
        private SenderDto sender;

        public NotificationRequestDtoBuilder code(String code) {
            this.code = code;
            return this;
        }

        public NotificationRequestDtoBuilder params(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public NotificationRequestDtoBuilder channels(List<String> channels) {
            this.channels = channels;
            return this;
        }

        public NotificationRequestDtoBuilder recipients(List<RecipientDto> recipients) {
            this.recipients = recipients;
            return this;
        }

        public NotificationRequestDtoBuilder sender(SenderDto sender) {
            this.sender = sender;
            return this;
        }

        public NotificationRequestDto build() {
            return new NotificationRequestDto(code, params, channels, recipients, sender);
        }
    }
}

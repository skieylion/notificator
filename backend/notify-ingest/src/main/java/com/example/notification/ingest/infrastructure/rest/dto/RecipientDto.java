package com.example.notification.ingest.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(description = "Получатель уведомления")
public class RecipientDto {

    @Schema(description = "Локаль получателя (например en, ru-RU)", example = "en")
    private String locale;

    @NotEmpty(message = "contacts must contain at least one element")
    @Valid
    @Schema(description = "Контакты получателя (минимум один)", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ContactDto> contacts;

    public RecipientDto() {}

    public RecipientDto(String locale, List<ContactDto> contacts) {
        this.locale = locale;
        this.contacts = contacts;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDto> contacts) {
        this.contacts = contacts;
    }

    public static RecipientDtoBuilder builder() {
        return new RecipientDtoBuilder();
    }

    public static class RecipientDtoBuilder {
        private String locale;
        private List<ContactDto> contacts;

        public RecipientDtoBuilder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public RecipientDtoBuilder contacts(List<ContactDto> contacts) {
            this.contacts = contacts;
            return this;
        }

        public RecipientDto build() {
            return new RecipientDto(locale, contacts);
        }
    }
}

package com.example.notification.ingest.infrastructure.messaging.dto;

import java.util.List;

public class RecipientPayload {
    private String locale;
    private List<ContactPayload> contacts;

    public RecipientPayload() {}

    public RecipientPayload(String locale, List<ContactPayload> contacts) {
        this.locale = locale;
        this.contacts = contacts;
    }

    public String getLocale() { return locale; }
    public void setLocale(String locale) { this.locale = locale; }

    public List<ContactPayload> getContacts() { return contacts; }
    public void setContacts(List<ContactPayload> contacts) { this.contacts = contacts; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String locale;
        private List<ContactPayload> contacts;

        public Builder locale(String locale) { this.locale = locale; return this; }
        public Builder contacts(List<ContactPayload> contacts) { this.contacts = contacts; return this; }

        public RecipientPayload build() {
            return new RecipientPayload(locale, contacts);
        }
    }
}

package com.example.notification.common.sender.impl;

import com.example.notification.common.sender.Sender;
import com.example.notification.common.sender.SenderType;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Отправитель — системный сервис.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemSender implements Sender, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Идентификатор сервиса (например "billing-service", "scheduler") */
    private String systemId;

    @Override
    public SenderType getType() {
        return SenderType.SYSTEM;
    }

    @Override
    public String getId() {
        return systemId;
    }
}

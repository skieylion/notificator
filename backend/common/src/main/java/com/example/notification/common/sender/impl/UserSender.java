package com.example.notification.common.sender.impl;

import com.example.notification.common.sender.Sender;
import com.example.notification.common.sender.SenderType;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Отправитель — реальный пользователь системы.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSender implements Sender, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Идентификатор пользователя */
    private String userId;

    @Override
    public SenderType getType() {
        return SenderType.USER;
    }

    @Override
    public String getId() {
        return userId;
    }
}

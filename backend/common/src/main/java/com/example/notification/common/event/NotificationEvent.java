package com.example.notification.common.event;

import com.example.notification.common.sender.Sender;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Событие уведомления для публикации в брокер.
 * Стабильный контракт: REST и gRPC адаптеры маппят свои DTO в этот класс.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Уникальный идентификатор уведомления (генерируется в ingest) */
    private String id;

    /** Ключ идемпотентности (из запроса) */
    private String idempotencyKey;

    /** Код уведомления/шаблона */
    private String code;

    /** Параметры для подстановки в шаблон */
    private Map<String, String> params;

    /** Каналы доставки (EMAIL, SMS, PUSH и т.д.). Опционально — null означает все подходящие. */
    private List<String> channels;

    /** Список получателей (минимум один) */
    private List<Recipient> recipients;

    /** Инициатор отправки (USER или SYSTEM) */
    private Sender sender;
}

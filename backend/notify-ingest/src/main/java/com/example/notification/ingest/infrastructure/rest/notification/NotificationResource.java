package com.example.notification.ingest.infrastructure.rest.notification;

import com.example.notification.common.event.NotificationEvent;
import com.example.notification.ingest.application.port.in.AcceptNotificationPort;
import com.example.notification.ingest.domain.IngestResult;
import com.example.notification.ingest.infrastructure.rest.dto.*;
import com.example.notification.ingest.infrastructure.rest.mapper.NotificationRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(value = "/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notifications", description = "Приём запросов на отправку уведомлений")
@RequiredArgsConstructor
public class NotificationResource {

    private final AcceptNotificationPort acceptNotificationPort;
    private final NotificationRestMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Создать уведомление",
            description = "Принимает запрос на отправку уведомления. " +
                    "Заголовок X-Idempotency-Key обязателен. " +
                    "Тело: code, params, recipients, sender."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Запрос принят",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = NotificationResponseDto.class)
            )
    )
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    public Mono<ResponseEntity<NotificationResponseDto>> createNotification(
            @Parameter(description = "Ключ идемпотентности", required = true)
            @RequestHeader("X-Idempotency-Key") String idempotencyKey,

            @Valid @RequestBody NotificationRequestDto requestDto
    ) {
        return Mono.fromCallable(() -> {
            if (idempotencyKey == null || idempotencyKey.isBlank()) {
                log.warn("Missing or empty X-Idempotency-Key header");
                throw new IllegalArgumentException("X-Idempotency-Key header is required");
            }

            NotificationEvent event = mapper.toEvent(requestDto, idempotencyKey);
            IngestResult result = acceptNotificationPort.accept(event);
            NotificationResponseDto response = mapper.toResponse(event.getId(), result);

            return ResponseEntity.ok(response);
        });
    }
}

package com.example.notification.ingest.infrastructure.rest.notification;

import com.example.notification.common.event.NotificationEvent;
import com.example.notification.ingest.application.port.in.AcceptNotificationPort;
import com.example.notification.ingest.domain.IngestResult;
import com.example.notification.ingest.infrastructure.rest.dto.*;
import com.example.notification.ingest.infrastructure.rest.mapper.NotificationRestMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Slf4j
@ApplicationScoped
@NotificationApi
@RequiredArgsConstructor
public class NotificationResource {

    private final AcceptNotificationPort acceptNotificationPort;
    private final NotificationRestMapper mapper;

    @POST
    @Operation(
            summary = "Создать уведомление",
            description = "Принимает запрос на отправку уведомления. " +
                    "Заголовок X-Idempotency-Key обязателен. " +
                    "Тело: code, params, recipients, sender."
    )
    @APIResponse(
            responseCode = "200",
            description = "Запрос принят",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = NotificationResponseDto.class)
            )
    )
    @APIResponse(responseCode = "400", description = "Неверный запрос")
    @APIResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    public Response createNotification(
            @Parameter(
                    description = "Ключ идемпотентности",
                    required = true,
                    in = ParameterIn.HEADER
            )
            @HeaderParam("X-Idempotency-Key") String idempotencyKey,

            @Valid NotificationRequestDto requestDto
    ) {
        // Validate idempotency key
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            log.warn("Missing or empty X-Idempotency-Key header");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"X-Idempotency-Key header is required\"}")
                    .build();
        }

        // Map to event and process
        NotificationEvent event = mapper.toEvent(requestDto, idempotencyKey);
        IngestResult result = acceptNotificationPort.accept(event);
        NotificationResponseDto response = mapper.toResponse(event.getId(), result);

        return Response.ok(response).build();
    }
}

package com.example.notification.ingest.infrastructure.rest;

import com.example.notification.common.messaging.EventPublishException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class RestExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof ConstraintViolationException cve) {
            String message = cve.getConstraintViolations().stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .reduce((a, b) -> a + "; " + b)
                    .orElse("Validation failed");
            log.warn("Validation error: {}", message);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + message + "\"}")
                    .build();
        }

        if (exception instanceof InvalidTypeIdException ite) {
            log.warn("Invalid type discriminator: {}", ite.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Invalid type. Supported: USER_ID, EMAIL, PHONE for contacts; USER, SYSTEM for sender\"}")
                    .build();
        }

        if (exception instanceof EventPublishException epe) {
            log.error("Failed to publish event: {}", epe.getMessage(), epe);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("{\"error\": \"Service temporarily unavailable\"}")
                    .build();
        }

        log.error("Unexpected error: {}", exception.getMessage(), exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Internal server error\"}")
                .build();
    }
}

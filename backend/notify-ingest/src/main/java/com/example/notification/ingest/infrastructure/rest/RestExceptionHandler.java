package com.example.notification.ingest.infrastructure.rest;

import com.example.notification.common.messaging.EventPublishException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .reduce((a, b) -> a + "; " + b)
                .orElse("Validation failed");
        log.warn("Validation error: {}", message);
        return Mono.just(ResponseEntity.badRequest().body(Map.of("error", message)));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleWebExchangeBindException(WebExchangeBindException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .reduce((a, b) -> a + "; " + b)
                .orElse("Validation failed");
        log.warn("Validation error: {}", message);
        return Mono.just(ResponseEntity.badRequest().body(Map.of("error", message)));
    }

    @ExceptionHandler(ServerWebInputException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleServerWebInputException(ServerWebInputException ex) {
        String reason = ex.getReason() != null ? ex.getReason() : "Bad request";
        log.warn("Input error: {}", reason);
        return Mono.just(ResponseEntity.badRequest().body(Map.of("error", reason)));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleIllegalArgument(IllegalArgumentException ex) {
        log.warn("Invalid argument: {}", ex.getMessage());
        return Mono.just(ResponseEntity.badRequest().body(Map.of("error", ex.getMessage())));
    }

    @ExceptionHandler(InvalidTypeIdException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleInvalidTypeId(InvalidTypeIdException ex) {
        log.warn("Invalid type discriminator: {}", ex.getMessage());
        return Mono.just(ResponseEntity.badRequest()
                .body(Map.of("error", "Invalid type. Supported: USER_ID, EMAIL, PHONE for contacts; USER, SYSTEM for sender")));
    }

    @ExceptionHandler(EventPublishException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleEventPublishException(EventPublishException ex) {
        log.error("Failed to publish event: {}", ex.getMessage(), ex);
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Service temporarily unavailable")));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, String>>> handleGenericException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Internal server error")));
    }
}

package com.samoilenko.onlinebookstore.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage)
                .toList();
        return getResponseEntity(status, errors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request
    ) {
        String bodyOfResponse = "Entity not found: " + ex.getMessage();
        log.error("Entity not found", ex);
        return getResponseEntity(HttpStatus.NOT_FOUND, bodyOfResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request
    ) {
        String bodyOfResponse = "User not found: " + ex.getMessage();
        log.error("User not found:", ex);
        return getResponseEntity(HttpStatus.NOT_FOUND, bodyOfResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(
            AccessDeniedException ex, WebRequest request
    ) {
        String bodyOfResponse = "Access denied: " + ex.getMessage();
        log.error("Access denied:", ex);
        return getResponseEntity(HttpStatus.FORBIDDEN, bodyOfResponse);
    }

    @ExceptionHandler(RegistrationException.class)
    protected ResponseEntity<Object> handleRegistrationException(
            RegistrationException ex, WebRequest request
    ) {
        String responseMessage = "Can't register user: " + ex.getMessage();
        log.error("Can't register user", ex);
        return getResponseEntity(HttpStatus.BAD_REQUEST, responseMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleFieldValidationException(
            IllegalArgumentException ex, WebRequest request
    ) {
        String responseMessage = "Can't complete validation:" + ex.getMessage();
        log.error("Can't complete validation", ex);
        return getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, responseMessage);
    }

    private ResponseEntity<Object> getResponseEntity(HttpStatusCode status, Object errors) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status);
        body.put("errors", errors);
        return new ResponseEntity<>(body, status);
    }

    private String getErrorMessage(ObjectError e) {
        if (e instanceof FieldError) {
            String field = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            return field + " " + message;
        }
        return e.getDefaultMessage();
    }
}

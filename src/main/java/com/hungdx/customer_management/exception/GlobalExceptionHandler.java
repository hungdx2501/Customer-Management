package com.hungdx.customer_management.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        log.warn("Email address already exists {}", exception.getMessage());
        Map<String, String> result = new HashMap<>();
        result.put("message", "Email address already exists");
        return ResponseEntity.badRequest().body(result);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException exception) {
        log.warn("User not found {}", exception.getMessage());
        Map<String, String> result = new HashMap<>();
        result.put("message", "User not found");
        return ResponseEntity.badRequest().body(result);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("Method argument not valid {}", exception.getMessage());
        Map<String, String> result = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                (error) -> {
                    result.put(error.getField(), error.getDefaultMessage());
                }
        );
        return ResponseEntity.badRequest().body(result);
    }
}

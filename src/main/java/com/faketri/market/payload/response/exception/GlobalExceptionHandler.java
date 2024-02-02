package com.faketri.market.payload.response.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

/**
 * Intercepting and handling Runtime errors
 */
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver =
                new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("JwtValidException", "token is doesn't valid");
        resolver.setOrder(-1);
        resolver.setExceptionMappings(mappings);
        return resolver;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppError> handleException(ResourceNotFoundException e
    ) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(PasswordNotValidException.class)
    public ResponseEntity<AppError> handleException(PasswordNotValidException e
    ) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(), e.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<AppError> handleException(
            ChangeSetPersister.NotFoundException e
    ) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<AppError> handleException(
            EmptyResultDataAccessException e
    ) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(JwtValidException.class)
    public ResponseEntity<AppError> handleException(JwtValidException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(), e.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

}

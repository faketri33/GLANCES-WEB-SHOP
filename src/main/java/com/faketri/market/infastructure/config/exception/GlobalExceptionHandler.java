package com.faketri.market.infastructure.config.exception;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.user.exception.PasswordNotValidException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Intercepting and handling Runtime errors
 *
 * @author Dmitriy Faketri
 */
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle exception response entity.
     *
     * @param e the e
     *
     * @return the response entity
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppError> handleException(ResourceNotFoundException e
    ) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Handle exception response entity.
     *
     * @param e the e
     *
     * @return the response entity
     */
    @ExceptionHandler(PasswordNotValidException.class)
    public ResponseEntity<AppErrorArray> handleException(
            PasswordNotValidException e
    ) {
        return new ResponseEntity<>(new AppErrorArray(HttpStatus.UNAUTHORIZED.value(),
                                                      Map.of("password",
                                                             e.getMessage()
                                                      )
        ), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle exception response entity.
     *
     * @param e the e
     *
     * @return the response entity
     */
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<AppError> handleException(
            ChangeSetPersister.NotFoundException e
    ) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Handle exception response entity.
     *
     * @param e the
     *
     * @return the response entity
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<AppError> handleException(
            EmptyResultDataAccessException e
    ) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Handle exception response entity.
     *
     * @param e the e
     *
     * @return the response entity
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<AppError> handleException(ExpiredJwtException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(), e.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

    /**
     * Handle exception response entity.
     *
     * @param e the e
     *
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorArray> handleException(
            MethodArgumentNotValidException e
    ) {
        return new ResponseEntity<>(new AppErrorArray(e.getStatusCode().value(),
                                                      e.getFieldErrors()
                                                       .stream()
                                                       .collect(Collectors.toMap(
                                                               FieldError::getField,
                                                               FieldError::getDefaultMessage
                                                       ))
        ), e.getStatusCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AppError> handleException(
            HttpMessageNotReadableException e
    ) throws IOException {
        return new ResponseEntity<>(new AppError(403,
                                                 Objects.requireNonNull(e.getHttpInputMessage()
                                                                         .getBody()
                                                                         .toString())
        ), HttpStatusCode.valueOf(403));
    }


}

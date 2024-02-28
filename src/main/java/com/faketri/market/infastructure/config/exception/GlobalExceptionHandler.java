package com.faketri.market.infastructure.config.exception;

import com.faketri.market.entity.user.exception.PasswordNotValidException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Objects;

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
    public ResponseEntity<AppError> handleException(PasswordNotValidException e
    ) {
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
     * @param e the e
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
    @ExceptionHandler(JwtValidException.class)
    public ResponseEntity<AppError> handleException(JwtValidException e) {
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
    public ResponseEntity<AppError> handleException(
            MethodArgumentNotValidException e
    ) {
        return new ResponseEntity<>(new AppError(
                e.getStatusCode().value(),
                Objects.requireNonNull(e.getDetailMessageArguments())[1].toString()
        ), e.getStatusCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AppError> handleException(
            HttpMessageNotReadableException e
    ) throws IOException {
        e.printStackTrace();
        System.out.println(e.toString());
        return new ResponseEntity<>(new AppError(
                403,
                Objects.requireNonNull(e.getHttpInputMessage()
                                        .getBody()
                                        .toString())
        ), HttpStatusCode.valueOf(403));
    }


}

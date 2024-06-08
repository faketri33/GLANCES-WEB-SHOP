package com.faketri.market.infastructure.config.exception;

import com.faketri.market.entity.exception.ImageFormatException;
import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.payload.product.exception.NotEnoughProductException;
import com.faketri.market.entity.product.payload.promotion.exception.ProductAlreadyParticipatesInPromoException;
import com.faketri.market.entity.user.payload.user.exception.PasswordNotValidException;
import com.faketri.market.entity.user.payload.user.exception.UserAlreadyExistsException;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Intercepting and handling Runtime errors
 *
 * @author Dmitriy Faketri
 */
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppError> handleException(ResourceNotFoundException e
    ) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

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
        System.out.println("OBRABOTAL");
        return new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(), e.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<AppError> handleException(ExpiredJwtException e) {
        System.out.println("OBRABOTAL EXPIRED");
        return new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(), e.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

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

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<AppErrorArray> handleException(
            UserAlreadyExistsException e
    ) {
        return new ResponseEntity<>(new AppErrorArray(HttpStatus.BAD_REQUEST.value(),
                Map.of("login", e.getMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughProductException.class)
    public ResponseEntity<AppError> handleException(NotEnoughProductException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<AppError> handleException(FileSizeLimitExceededException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Слишкой большой размер файла."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageFormatException.class)
    public ResponseEntity<AppError> handleException(ImageFormatException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductAlreadyParticipatesInPromoException.class)
    public ResponseEntity<AppError> handleException(ProductAlreadyParticipatesInPromoException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

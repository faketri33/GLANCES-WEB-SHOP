package com.faketri.market.payload.response.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Intercepting and handling Runtime errors
 * */
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppError> handleException(ResourceNotFoundException e){
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PasswordNotValidException.class)
    public ResponseEntity<AppError> handleException(PasswordNotValidException e){
        return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<AppError> handleException(ChangeSetPersister.NotFoundException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<AppError> handleException(EmptyResultDataAccessException e){
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.toString()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(JwtExpiredException.class)
    public ResponseEntity<AppError> handleException(JwtExpiredException e){
        return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

}

package com.faketri.market.payload.response.exception;

public class PasswordNotValidException extends RuntimeException {
    public PasswordNotValidException(String message) {
        super(message);
    }
}

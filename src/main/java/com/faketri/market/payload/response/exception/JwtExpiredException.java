package com.faketri.market.payload.response.exception;

public class JwtExpiredException extends RuntimeException{
    public JwtExpiredException(String message) {
        super(message);
    }
}

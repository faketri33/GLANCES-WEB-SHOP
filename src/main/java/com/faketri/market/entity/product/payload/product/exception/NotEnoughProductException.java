package com.faketri.market.entity.product.payload.product.exception;

public class NotEnoughProductException extends RuntimeException{

    public NotEnoughProductException(String message) {
        super(message);
    }
}

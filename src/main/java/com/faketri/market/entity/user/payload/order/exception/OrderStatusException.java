package com.faketri.market.entity.user.payload.order.exception;

public class OrderStatusException extends RuntimeException {

    public OrderStatusException(String message) {
        super(message);
    }
}

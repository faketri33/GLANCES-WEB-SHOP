package com.faketri.market.entity.product.payload.promotion.exception;

public class ProductAlreadyParticipatesInPromoException extends RuntimeException{

    public ProductAlreadyParticipatesInPromoException(String message) {
        super(message);
    }
}

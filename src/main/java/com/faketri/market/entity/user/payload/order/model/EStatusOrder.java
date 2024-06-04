package com.faketri.market.entity.user.payload.order.model;

/**
 * The enum E status order.
 *
 * @author Dmitriy Faketri
 */
public enum EStatusOrder {
    RECEIVED("Выдан"),
    DELIVERED("Доставлен"),
    IN_DELIVERING("В пути");

    private final String status;

    EStatusOrder(String s) {
        this.status = s;
    }

    public String getStatus(){
        return status;
    }
}

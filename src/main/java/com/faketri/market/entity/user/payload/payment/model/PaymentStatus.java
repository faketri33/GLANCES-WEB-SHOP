package com.faketri.market.entity.user.payload.payment.model;

public enum PaymentStatus {
    AWAITING_PAID("Ожидает оплаты"),
    PAID("Оплачен");

    private final String status;

    PaymentStatus(String s) {
        this.status = s;
    }

    public String getStatus() {
        return status;
    }
}

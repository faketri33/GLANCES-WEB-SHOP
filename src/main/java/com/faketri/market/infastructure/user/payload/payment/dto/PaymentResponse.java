package com.faketri.market.infastructure.user.payload.payment.dto;

import com.faketri.market.entity.user.payload.payment.model.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentResponse {

    private UUID id;
    private String paymentStatus;
    private LocalDateTime dateOfCreate;
    private LocalDateTime dateOfPayment;

    public PaymentResponse(UUID id, PaymentStatus paymentStatus, LocalDateTime dateOfCreate, LocalDateTime dateOfPayment) {
        this.id = id;
        this.paymentStatus = paymentStatus.getStatus();
        this.dateOfCreate = dateOfCreate;
        this.dateOfPayment = dateOfPayment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public LocalDateTime getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDateTime dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }
}

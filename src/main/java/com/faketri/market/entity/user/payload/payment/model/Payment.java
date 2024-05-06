package com.faketri.market.entity.user.payload.payment.model;

import com.faketri.market.entity.user.payload.user.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;
    private PaymentStatus paymentStatus = PaymentStatus.AWAITING_PAID;
    private LocalDateTime dateOfCreate;
    private LocalDateTime dateOfPayment;

    public Payment() {
    }

    public Payment(UUID id, Users user, PaymentStatus paymentStatus,
                   LocalDateTime dateOfCreate, LocalDateTime dateOfPayment) {
        this.id = id;
        this.user = user;
        this.paymentStatus = paymentStatus;
        this.dateOfCreate = dateOfCreate;
        this.dateOfPayment = dateOfPayment;
    }

    @PrePersist
    public void prePersist() {
        this.dateOfCreate = LocalDateTime.now();
        this.paymentStatus = PaymentStatus.AWAITING_PAID;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
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

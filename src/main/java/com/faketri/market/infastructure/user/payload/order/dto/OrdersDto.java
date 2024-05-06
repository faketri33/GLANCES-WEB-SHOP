package com.faketri.market.infastructure.user.payload.order.dto;

import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.entity.user.payload.order.model.EStatusOrder;
import com.faketri.market.entity.user.payload.payment.model.Payment;
import com.faketri.market.infastructure.user.payload.user.dto.UserSmallDataResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrdersDto {

    private UUID id;

    private UserSmallDataResponse users;

    private List<ProductItem> products = new ArrayList<>();

    private LocalDateTime dateOfCreate;

    private LocalDateTime dateOfRelease;

    private Integer price;

    private Payment payment = new Payment();

    private EStatusOrder statusOrder = EStatusOrder.IN_DELIVERING;

    public OrdersDto() {
    }

    public OrdersDto(UUID id, UserSmallDataResponse users, List<ProductItem> products, LocalDateTime dateOfCreate,
                     LocalDateTime dateOfRelease, Integer price, Payment payment, EStatusOrder statusOrder) {
        this.id = id;
        this.users = users;
        this.products = products;
        this.dateOfCreate = dateOfCreate;
        this.dateOfRelease = dateOfRelease;
        this.price = price;
        this.payment = payment;
        this.statusOrder = statusOrder;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserSmallDataResponse getUsers() {
        return users;
    }

    public void setUsers(UserSmallDataResponse users) {
        this.users = users;
    }

    public List<ProductItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductItem> products) {
        this.products = products;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public LocalDateTime getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDateTime dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public EStatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(EStatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }
}

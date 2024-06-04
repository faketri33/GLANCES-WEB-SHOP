package com.faketri.market.infastructure.user.payload.basket.dto;

import java.util.UUID;

public class UpdateQuantityRequest {

    private UUID productItemId;
    private Integer quantity;

    public UUID getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(UUID productItemId) {
        this.productItemId = productItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

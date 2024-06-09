package com.faketri.market.infastructure.product.payload.brand.dto;

import java.util.UUID;

public class BrandRequest {

    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BrandRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

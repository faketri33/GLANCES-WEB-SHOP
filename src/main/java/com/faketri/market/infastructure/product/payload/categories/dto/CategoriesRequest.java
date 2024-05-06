package com.faketri.market.infastructure.product.payload.categories.dto;

public class CategoriesRequest {

    String name;

    public CategoriesRequest() {
    }

    public CategoriesRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

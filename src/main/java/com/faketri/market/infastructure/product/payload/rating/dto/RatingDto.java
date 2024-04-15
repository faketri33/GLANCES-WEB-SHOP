package com.faketri.market.infastructure.product.payload.rating.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

public class RatingDto {

    @Column
    private String description;
    @Column
    private Short grade;
    @ManyToOne
    private String productId;
    @ManyToOne
    private String userId;

    public RatingDto() {
    }

    public RatingDto(String description, Short grade, String productId, String userId) {
        this.description = description;
        this.grade = grade;
        this.productId = productId;
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

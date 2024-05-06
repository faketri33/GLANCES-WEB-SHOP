package com.faketri.market.infastructure.product.payload.rating.dto;

import com.faketri.market.entity.image.model.Image;

import java.util.UUID;

public class RatingDtoResponse {

    private UUID uuid;
    private String description;
    private Short grade;
    private Image userImage;
    private String username;

    public RatingDtoResponse() {
    }

    public RatingDtoResponse(UUID uuid, String description, Short grade, Image userImage, String username) {
        this.uuid = uuid;
        this.description = description;
        this.grade = grade;
        this.userImage = userImage;
        this.username = username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public Image getUserImage() {
        return userImage;
    }

    public void setUserImage(Image userImage) {
        this.userImage = userImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

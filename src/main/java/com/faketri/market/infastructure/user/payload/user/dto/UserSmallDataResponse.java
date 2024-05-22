package com.faketri.market.infastructure.user.payload.user.dto;

import com.faketri.market.entity.image.model.Image;

import java.util.UUID;

public class UserSmallDataResponse {

    private UUID id;

    private Image profileImage;

    private String login;

    private String city;

    public UserSmallDataResponse() {
    }

    public UserSmallDataResponse(UUID id, Image profileImage, String login, String city) {
        this.id = id;
        this.profileImage = profileImage;
        this.login = login;
        this.city = city;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

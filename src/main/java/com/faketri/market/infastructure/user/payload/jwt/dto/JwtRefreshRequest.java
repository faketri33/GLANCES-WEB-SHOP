package com.faketri.market.infastructure.user.payload.jwt.dto;

public class JwtRefreshRequest {

    private String refreshToken;

    public JwtRefreshRequest() {
    }

    public JwtRefreshRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

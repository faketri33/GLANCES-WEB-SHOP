package com.faketri.market.infastructure.user.payload.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * The type Jwt authentication response.
 *
 * @author Dmitriy Faketri
 */
@Schema(description = "Ответ c токеном доступа")
public class JwtAuthenticationResponse {

    @Schema(description = "Токен доступа",
            example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMjUwNj...")
    private String accessToken;
    private String refreshToken;

    private final String type = "Bearer ";

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getType() {
        return type;
    }

}

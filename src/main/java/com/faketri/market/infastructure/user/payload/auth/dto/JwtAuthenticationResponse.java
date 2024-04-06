package com.faketri.market.infastructure.user.payload.auth.dto;

import com.faketri.market.infastructure.user.payload.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


/**
 * The type Jwt authentication response.
 *
 * @author Dmitriy Faketri
 */
@Schema(description = "Ответ c токеном доступа")
public class JwtAuthenticationResponse {

    private UserResponse user;
    @Schema(description = "Токен доступа",
            example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMjUwNj...")
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expiration;
    private final String type = "Bearer ";

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String accessToken, String refreshToken, LocalDateTime expiration) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }

    public JwtAuthenticationResponse(UserResponse user, String accessToken, String refreshToken, LocalDateTime expiration) {
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
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

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }
}

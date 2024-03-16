package com.faketri.market.infastructure.config.web.authentication.dto;

import com.faketri.market.infastructure.userPayload.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;


/**
 * The type Jwt authentication response.
 *
 * @author Dmitriy Faketri
 */
@Schema(description = "Ответ c токеном доступа")
public class JwtAuthenticationResponse {

    @Schema(description = "Пользователь")
    private UserResponse user;
    @Schema(description = "Токен доступа",
            example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMjUwNj...")
    private String token;

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(UserResponse user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Bearer " + token;
    }

}

package com.faketri.market.infastructure.userPayload.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * The type Sign up request.
 *
 * @author Dmitriy Faketri
 */
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {

    /**
     * The Login.
     */
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 5, max = 50,
            message = "Имя пользователя должно содержать от 5 до 50 символов")
    String login;

    /**
     * The Email.
     */
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    String email;

    /**
     * The Password.
     */
    @Size(min = 8, message = "Пароль должен содержать мининум 8 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    String password;


    public SignUpRequest() {
    }

    public SignUpRequest(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SignUpRequest that = (SignUpRequest) object;
        return Objects.equals(login, that.login) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, password);
    }
}
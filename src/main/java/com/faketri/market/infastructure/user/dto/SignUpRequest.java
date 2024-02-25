package com.faketri.market.infastructure.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * The type Sign up request.
 *
 * @author Dmitriy Faketri
 */
@Data
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

}
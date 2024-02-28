package com.faketri.market.infastructure.config.web.authentication.controller;

import com.faketri.market.infastructure.config.web.authentication.AuthService;
import com.faketri.market.infastructure.config.web.authentication.dto.JwtAuthenticationResponse;
import com.faketri.market.infastructure.user.dto.SignInRequest;
import com.faketri.market.infastructure.user.dto.SignUpRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController()
@CrossOrigin({ "http://localhost:8081", "http://192.168.1.106:8081/" })
@RequestMapping(value = "/api/auth", method = RequestMethod.POST)
@Tag(name = "Auth", description = "...")
public class Post {

    private final AuthService authService;

    public Post(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/sing-in")
    public @ResponseBody JwtAuthenticationResponse singIn(
            @RequestBody @Valid SignInRequest signInRequest
    ) {
        return authService.signIn(signInRequest);
    }

    @RequestMapping(value = "/sing-up")
    public @ResponseBody JwtAuthenticationResponse singUp(
            @RequestBody @Valid SignUpRequest signUpRequest
    ) {
        return authService.signUp(signUpRequest);
    }

}

package com.faketri.market.infastructure.config.web.authentication.controller;

import com.faketri.market.infastructure.config.web.authentication.gateway.AuthService;
import com.faketri.market.infastructure.user.dto.SignInRequest;
import com.faketri.market.infastructure.user.dto.SignUpRequest;
import com.faketri.market.infastructure.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Auth", description = "...")
public class AuthPostController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AuthService authService;

    public AuthPostController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/sing-in")
    public ResponseEntity<UserResponse> singIn(
            @RequestBody @Valid SignInRequest signInRequest
    ) {
        log.info("Request to sing in from user with login " + signInRequest.getLogin());
        var authenticationResponse = authService.signIn(signInRequest);
        return ResponseEntity.ok()
                .header("Authorization",
                        authenticationResponse.toString()
                )
                .body(authenticationResponse.getUser());
    }

    @RequestMapping("/sing-up")
    public ResponseEntity<UserResponse> singUp(
            @RequestBody @Valid SignUpRequest signUpRequest
    ) {
        log.info("Request to sing up from user with login " + signUpRequest.getLogin());
        var authenticationResponse = authService.signUp(signUpRequest);
        return ResponseEntity.ok()
                .header("Authorization",
                        authenticationResponse.toString()
                )
                .body(authenticationResponse.getUser());
    }

}

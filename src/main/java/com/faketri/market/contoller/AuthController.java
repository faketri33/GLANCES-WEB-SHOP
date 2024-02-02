package com.faketri.market.contoller;

import com.faketri.market.payload.request.SignInRequest;
import com.faketri.market.payload.request.SignUpRequest;
import com.faketri.market.payload.response.JwtAuthenticationResponse;
import com.faketri.market.payload.response.exception.JwtValidException;
import com.faketri.market.service.user.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/auth")
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/auth")
@Tag(name = "auth", description = "signIn || signUp")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(path = "/register", method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtAuthenticationResponse register(
            @RequestBody @Valid SignUpRequest signUpRequest
    ) {
        return authService.signUp(signUpRequest);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> login(
            @RequestBody @Valid SignInRequest signInRequest
    ) {
        System.out.println(signInRequest);

        try {
            return ResponseEntity.ok(authService.signIn(signInRequest));
        } catch (Exception e) {
            throw new JwtValidException(e.getMessage());
        }

    }

}

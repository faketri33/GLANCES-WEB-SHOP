package com.faketri.market.infastructure.user.payload.auth.gateway;

import com.faketri.market.infastructure.user.payload.auth.dto.JwtAuthenticationResponse;
import com.faketri.market.infastructure.user.payload.user.dto.SignInRequest;
import com.faketri.market.infastructure.user.payload.user.dto.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse getAccessToken(String refreshToken);

    JwtAuthenticationResponse getRefreshToken(String refreshToken);
}

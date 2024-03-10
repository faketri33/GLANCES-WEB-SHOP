package com.faketri.market.infastructure.config.web.authentication.gateway;

import com.faketri.market.infastructure.config.web.authentication.dto.JwtAuthenticationResponse;
import com.faketri.market.infastructure.user.dto.SignInRequest;
import com.faketri.market.infastructure.user.dto.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest);

}

package com.faketri.market.infastructure.config.web.authentication.gateway;

import com.faketri.market.infastructure.config.web.authentication.dto.JwtAuthenticationResponse;
import com.faketri.market.infastructure.user.dto.SignInRequest;
import com.faketri.market.infastructure.user.dto.SignUpRequest;

public interface AuthService {

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest);

}

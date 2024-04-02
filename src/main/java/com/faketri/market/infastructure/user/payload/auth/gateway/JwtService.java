package com.faketri.market.infastructure.user.payload.auth.gateway;

import com.faketri.market.infastructure.user.payload.auth.dto.JwtAuthenticationResponse;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {

    String extractUserName(String token);

    JwtAuthenticationResponse generateToken(UserDetails user);

    String generateAccessToken(UserDetails user);

    Claims getAccessClaims(String token);

    Claims getRefreshClaims(String token);

    boolean validateAccessToken(String accessToken);

    boolean validateRefreshToken(String refreshToken);

}

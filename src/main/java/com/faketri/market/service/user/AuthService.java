package com.faketri.market.service.user;

import com.faketri.market.domain.users.ERole;
import com.faketri.market.domain.users.User;
import com.faketri.market.payload.request.SignInRequest;
import com.faketri.market.payload.request.SignUpRequest;
import com.faketri.market.payload.response.JwtAuthenticationResponse;
import com.faketri.market.service.jwt.JwtService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserDetailsServerImpl userDetailsServer;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest){
        User user = new User();
        user.setLogin(signUpRequest.getLogin());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.getRole().add(ERole.STANDART);

        user = userDetailsServer.getUserService().save(user);

        var jwt = jwtService.generateToken(userDetailsServer.generateUserDetails(user));
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getLogin(),
                    request.getPassword()
        ));
        var user = userDetailsServer
                .loadUserByUsername(request.getLogin());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}

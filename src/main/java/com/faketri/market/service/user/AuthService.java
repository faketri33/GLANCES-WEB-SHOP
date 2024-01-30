package com.faketri.market.service.user;

import com.faketri.market.domain.users.ERole;
import com.faketri.market.domain.users.User;
import com.faketri.market.payload.request.SignInRequest;
import com.faketri.market.payload.request.SignUpRequest;
import com.faketri.market.payload.response.JwtAuthenticationResponse;
import com.faketri.market.payload.response.exception.PasswordNotValidException;
import com.faketri.market.service.jwt.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
*  Сервис аутентификации пользователей
* */
@Log4j2
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

    /**
     *  Register user and inserting data in database
     * @param signUpRequest  login, email, password from body request
     * @return JwtAuthenticationResponse
     * */
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest){

        User user = new User();

        user.setLogin(signUpRequest.getLogin());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.getRole().add(ERole.STANDART);

        user = userDetailsServer.getUserService().save(user);

        var jwt = jwtService.generateToken(userDetailsServer.generateUserDetails(user));
        log.info(String.format("user register with name %s", user.getLogin()));
        return new JwtAuthenticationResponse(jwt);
    }
    /**
     *  Authorization user, generate jwt token.
     * @param signInRequest  login, password from body request
     * @return JwtAuthenticationResponse
     * */
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        var user = userDetailsServer
                .loadUserByUsername(signInRequest.getLogin());
        System.out.println(user);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    ));
        }catch (BadCredentialsException e){
            log.error(String.format("User not sign in with login %s , error - %s", user.getUsername(), e.getMessage()));
            throw new PasswordNotValidException("Not correct data for login");
        }
        var jwt = jwtService.generateToken(user);
        log.info(String.format("User sign in with login %s", user.getUsername()));
        return new JwtAuthenticationResponse(jwt);
    }
}

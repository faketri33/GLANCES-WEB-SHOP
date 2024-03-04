package com.faketri.market.usecase.auth;

import com.faketri.market.entity.user.exception.PasswordNotValidException;
import com.faketri.market.entity.user.model.ERole;
import com.faketri.market.entity.user.model.Users;
import com.faketri.market.infastructure.config.web.authentication.dto.JwtAuthenticationResponse;
import com.faketri.market.infastructure.config.web.authentication.gateway.AuthService;
import com.faketri.market.infastructure.user.dto.SignInRequest;
import com.faketri.market.infastructure.user.dto.SignUpRequest;
import com.faketri.market.infastructure.user.dto.UserResponse;
import com.faketri.market.usecase.user.UserDetailsServerImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Сервис аутентификации пользователей
 *
 * @author Dmitriy Faketri
 */
@Log4j2
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDetailsServerImpl userDetailsServer;
    @Autowired
    private PasswordEncoder       passwordEncoder;
    @Autowired
    private JwtServiceImpl        jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Register user and inserting data in database
     *
     * @param signUpRequest login, email, password from body request
     *
     * @return JwtAuthenticationResponse jwt authentication response
     */
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {

        Users users = new Users();

        users.setLogin(signUpRequest.getLogin());
        users.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        users.setEmail(signUpRequest.getEmail());
        users.getRole().add(ERole.CUSTOMER);

        users = userDetailsServer.getUserService().save(users);

        var jwt =
                jwtService.generateToken(userDetailsServer.generateUserDetails(
                        users));
        log.info("user register with login " + users.getLogin());
        return new JwtAuthenticationResponse(UserResponse.mapUser(users), jwt);
    }

    /**
     * Authorization user, generate jwt token.
     *
     * @param signInRequest login, password from body request
     *
     * @return JwtAuthenticationResponse jwt authentication response
     */
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getLogin(),
                                                                                       signInRequest.getPassword()
            ));
        } catch (BadCredentialsException ex) {
            log.error("Not valid password from user with login " + signInRequest.getLogin());
            throw new PasswordNotValidException("Неверный пароль");
        }

        var user = userDetailsServer.getUserService()
                                    .findByLogin(signInRequest.getLogin());

        var jwt =
                jwtService.generateToken(userDetailsServer.generateUserDetails(
                        user));
        log.info(String.format("User sign in with login %s", user.getLogin()));
        return new JwtAuthenticationResponse(UserResponse.mapUser(user), jwt);
    }

}

package com.faketri.market.usecase.auth;

import com.faketri.market.entity.user.exception.PasswordNotValidException;
import com.faketri.market.entity.user.gateway.mapper.UserMapper;
import com.faketri.market.entity.user.model.ERole;
import com.faketri.market.entity.user.model.Users;
import com.faketri.market.infastructure.config.web.authentication.dto.JwtAuthenticationResponse;
import com.faketri.market.infastructure.config.web.authentication.gateway.AuthService;
import com.faketri.market.infastructure.user.dto.SignInRequest;
import com.faketri.market.infastructure.user.dto.SignUpRequest;
import com.faketri.market.usecase.user.UserDetailsServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class AuthServiceImpl implements AuthService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserDetailsServerImpl userDetailsServer;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(UserDetailsServerImpl userDetailsServer, PasswordEncoder passwordEncoder, JwtServiceImpl jwtService, AuthenticationManager authenticationManager) {
        this.userDetailsServer = userDetailsServer;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Register user and inserting data in database
     *
     * @param signUpRequest login, email, password from body request
     * @return JwtAuthenticationResponse jwt authentication response
     */
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {

        Users user = new Users();

        user.setLogin(signUpRequest.getLogin());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.getRole().add(ERole.CUSTOMER);

        user = userDetailsServer.getUserService().save(user);

        var jwt =
                jwtService.generateToken(userDetailsServer.generateUserDetails(
                        user));
        log.info("User sign up with login " + user.getLogin());
        return new JwtAuthenticationResponse(UserMapper.toDto(user), jwt);
    }

    /**
     * Authorization user, generate jwt token.
     *
     * @param signInRequest login, password from body request
     * @return JwtAuthenticationResponse jwt authentication response
     */
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getLogin(),
                    signInRequest.getPassword()
            ));
        } catch (BadCredentialsException ex) {
            log.error(String.format("Not valid password from user with login %s", signInRequest.getLogin()));
            throw new PasswordNotValidException("Неверный пароль");
        }

        var user = userDetailsServer.getUserService()
                .findByLogin(signInRequest.getLogin());

        var jwt =
                jwtService.generateToken(userDetailsServer.generateUserDetails(
                        user));
        log.info("User sign in with login " + user.getLogin());
        return new JwtAuthenticationResponse(UserMapper.toDto(user), jwt);
    }

}

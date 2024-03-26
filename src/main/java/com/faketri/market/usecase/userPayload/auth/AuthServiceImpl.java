package com.faketri.market.usecase.userPayload.auth;

import com.faketri.market.entity.userPayload.user.exception.PasswordNotValidException;
import com.faketri.market.entity.userPayload.user.exception.UserAlreadyExistsException;
import com.faketri.market.entity.userPayload.user.gateway.mapper.UserMapper;
import com.faketri.market.entity.userPayload.user.model.ERole;
import com.faketri.market.entity.userPayload.user.model.Users;
import com.faketri.market.infastructure.config.web.authentication.dto.JwtAuthenticationResponse;
import com.faketri.market.infastructure.config.web.authentication.gateway.AuthService;
import com.faketri.market.infastructure.userPayload.user.dto.SignInRequest;
import com.faketri.market.infastructure.userPayload.user.dto.SignUpRequest;
import com.faketri.market.usecase.userPayload.user.UserDetailsServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final String SALT;

    @Autowired
    public AuthServiceImpl(UserDetailsServerImpl userDetailsServer, PasswordEncoder passwordEncoder,
                           JwtServiceImpl jwtService, AuthenticationManager authenticationManager,
                           @Value("${SALT}") String salt) {
        this.userDetailsServer = userDetailsServer;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.SALT = salt;
    }

    /**
     * Register user and inserting data in database
     *
     * @param signUpRequest login, email, password from body request
     * @return JwtAuthenticationResponse jwt authentication response
     */
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {

        if (userDetailsServer.getUserService().existsByLogin(signUpRequest.getLogin()))
            throw new UserAlreadyExistsException("Пользователь с таким логином уже существует.");
        if (userDetailsServer.getUserService().existsByEmail(signUpRequest.getEmail()))
            throw new UserAlreadyExistsException("Пользователь с такой почтой уже существует.");

        Users user = new Users();

        user.setLogin(signUpRequest.getLogin());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword() + SALT));
        user.setEmail(signUpRequest.getEmail());
        user.getRole().add(ERole.CUSTOMER);

        user = userDetailsServer.getUserService().save(user);

        var jwt =
                jwtService.generateToken(userDetailsServer.generateUserDetails(
                        user));
        log.info("User sign up with login " + user.getLogin());
        return new JwtAuthenticationResponse(UserMapper.toResponse(user), jwt);
    }

    /**
     * Authorization user, generate jwt token.
     *
     * @param signInRequest login, password from body request
     * @return JwtAuthenticationResponse jwt authentication response
     */
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getLogin(),
                    signInRequest.getPassword() + SALT
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
        return new JwtAuthenticationResponse(UserMapper.toResponse(user), jwt);
    }

}

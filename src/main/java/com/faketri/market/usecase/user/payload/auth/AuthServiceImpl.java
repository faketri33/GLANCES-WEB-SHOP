package com.faketri.market.usecase.user.payload.auth;

import com.faketri.market.entity.user.payload.jwt.model.JwtRefresh;
import com.faketri.market.entity.user.payload.user.exception.PasswordNotValidException;
import com.faketri.market.entity.user.payload.user.exception.UserAlreadyExistsException;
import com.faketri.market.entity.user.payload.user.gateway.mapper.UserMapper;
import com.faketri.market.entity.user.payload.user.model.ERole;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.config.exception.JwtValidException;
import com.faketri.market.infastructure.user.payload.auth.dto.JwtAuthenticationResponse;
import com.faketri.market.infastructure.user.payload.auth.gateway.AuthService;
import com.faketri.market.infastructure.user.payload.auth.gateway.JwtService;
import com.faketri.market.infastructure.user.payload.jwt.gateway.JwtRefreshService;
import com.faketri.market.infastructure.user.payload.user.dto.SignInRequest;
import com.faketri.market.infastructure.user.payload.user.dto.SignUpRequest;
import com.faketri.market.usecase.user.payload.user.UserDetailsServerImpl;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JwtRefreshService jwtRefreshService;
    private final String SALT;

    @Autowired
    public AuthServiceImpl(UserDetailsServerImpl userDetailsServer, PasswordEncoder passwordEncoder,
                           JwtService jwtService, AuthenticationManager authenticationManager,
                           JwtRefreshService jwtRefreshService,
                           @Value("${security.password.salt}") String salt) {
        this.userDetailsServer = userDetailsServer;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.jwtRefreshService = jwtRefreshService;
        this.SALT = salt;
    }

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

        final Users finalUser = userDetailsServer.getUserService().save(user);

        log.info("User sign up with login " + finalUser.getLogin());
        return generatedJwt(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getLogin(),
                            signInRequest.getPassword() + SALT
                    ));
        } catch (BadCredentialsException ex) {
            log.error(String.format("Not valid password from user with login %s", signInRequest.getLogin()));
            throw new PasswordNotValidException("Неверный пароль");
        }

        final Users user = userDetailsServer.getUserService().findByLogin(signInRequest.getLogin());

        log.info("User sign in with login " + user.getLogin());
        return generatedJwt(user);
    }

    public JwtAuthenticationResponse getAccessToken(String refreshToken) {
        if (!jwtService.validateRefreshToken(refreshToken))
            throw new JwtValidException("Невалидный JWT token");
        final Claims claims = jwtService.getRefreshClaims(refreshToken);
        final String login = claims.getSubject();
        final JwtRefresh saveRefreshToken = jwtRefreshService.findByUserLogin(login)
                .orElseThrow(() -> new JwtValidException("Token for user " + login + " not found"));

        if (!saveRefreshToken.getToken().equals(refreshToken))
            throw new JwtValidException("JWT token не совпадает");


        final UserDetails user = userDetailsServer.loadUserByUsername(login);
        return new JwtAuthenticationResponse(
                jwtService.generateAccessToken(user),
                null,
                null);
    }

    public JwtAuthenticationResponse getRefreshToken(String refreshToken) {
        if (!jwtService.validateRefreshToken(refreshToken))
            throw new JwtValidException("Невалидный JWT токен");

        final Claims claims = jwtService.getRefreshClaims(refreshToken);
        final String login = claims.getSubject();
        final JwtRefresh saveRefreshToken = jwtRefreshService.findByUserLogin(login)
                .orElseThrow(() -> new JwtValidException("Токен для пользователя - " + login + " не найден"));

        if (!saveRefreshToken.getToken().equals(refreshToken))
            throw new JwtValidException("Невалидный JWT токен");

        final Users user = userDetailsServer.getUserService().findByLogin(login);
        return generatedJwt(user);
    }

    private JwtAuthenticationResponse generatedJwt(Users user) {
        
        final JwtAuthenticationResponse authenticationResponse = jwtService.generateToken(userDetailsServer.generateUserDetails(user));
        authenticationResponse.setUser(UserMapper.toResponse(user));
        
        final JwtRefresh jwtRefresh = jwtRefreshService
                .findByUserLogin(user.getLogin())
                .orElse(new JwtRefresh());
        
        jwtRefresh.setUser(user);
        jwtRefresh.setToken(authenticationResponse.getRefreshToken());
        jwtRefresh.setDateOfExpiration(authenticationResponse.getExpiration());
        jwtRefreshService.save(jwtRefresh);
        
        return authenticationResponse;
    }

}

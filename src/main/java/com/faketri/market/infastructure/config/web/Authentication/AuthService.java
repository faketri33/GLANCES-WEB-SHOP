package com.faketri.market.infastructure.config.web.Authentication;

import com.faketri.market.entity.user.model.ERole;
import com.faketri.market.entity.user.model.Users;
import com.faketri.market.infastructure.config.web.Authentication.dto.JwtAuthenticationResponse;
import com.faketri.market.infastructure.user.dto.SignInRequest;
import com.faketri.market.infastructure.user.dto.SignUpRequest;
import com.faketri.market.infastructure.user.gateway.UserDetailsServerImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
public class AuthService {

    @Autowired
    private UserDetailsServerImpl userDetailsServer;
    @Autowired
    private PasswordEncoder       passwordEncoder;
    @Autowired
    private JwtService            jwtService;
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

        System.out.println(users);

        users = userDetailsServer.getUserService().save(users);

        var jwt =
                jwtService.generateToken(userDetailsServer.generateUserDetails(
                        users));
        log.info(String.format("user register with name %s", users.getLogin()));
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Authorization user, generate jwt token.
     *
     * @param signInRequest login, password from body request
     *
     * @return JwtAuthenticationResponse jwt authentication response
     */
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        /*try {*/
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getLogin(),
                                                                                   signInRequest.getPassword()
        ));
        /*}catch (BadCredentialsException e){
            log.error(String.format("User not sign in with login %s , error - %s", user.getUsername(), e.getMessage()));
            throw new PasswordNotValidException("Not correct data for login");
        }*/
        var user =
                userDetailsServer.loadUserByUsername(signInRequest.getLogin());
        System.out.println(user);

        var jwt = jwtService.generateToken(user);
        log.info(String.format("User sign in with login %s",
                               user.getUsername()
        ));
        return new JwtAuthenticationResponse(jwt);
    }

}

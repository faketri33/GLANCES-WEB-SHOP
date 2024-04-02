package com.faketri.market.infastructure.user.payload.user.controller;

import com.faketri.market.entity.user.payload.user.gateway.mapper.UserMapper;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.user.payload.user.dto.UserResponse;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The type User controller.
 *
 * @author Dmitriy Faketri
 */
@RestController
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
public class UserGetController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserGetController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Found users.
     *
     * @return the users
     */
    @RequestMapping("/")
    public UserResponse findByLogin(@RequestParam String login) {
        log.info(String.format("get user with login - %s", login));
        Users user = userService.findByLogin(login);
        System.out.println(user.getPassword());
        return UserMapper.toResponse(user);
    }

    @RequestMapping("/{id}")
    public UserResponse findById(@RequestParam UUID id) {
        return UserMapper.toResponse(userService.findById(id));
    }
}

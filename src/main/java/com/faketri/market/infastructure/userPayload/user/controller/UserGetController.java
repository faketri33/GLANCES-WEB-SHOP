package com.faketri.market.infastructure.userPayload.user.controller;

import com.faketri.market.entity.userPayload.user.gateway.mapper.UserMapper;
import com.faketri.market.infastructure.userPayload.user.dto.UserResponse;
import com.faketri.market.infastructure.userPayload.user.gateway.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @Autowired
    public UserGetController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Found users.
     *
     * @return the users
     */
    @RequestMapping("/")
    public UserResponse findByLogin(@RequestParam String login) {
        log.info(String.format("get user with login - %s", login));
        return UserMapper.toResponse(userService.findByLogin(login));
    }

    @RequestMapping("/{id}")
    public UserResponse findById(@RequestParam UUID id) {
        return UserMapper.toResponse(userService.findById(id));
    }
}

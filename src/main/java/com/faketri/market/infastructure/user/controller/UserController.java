package com.faketri.market.infastructure.user.controller;

import com.faketri.market.infastructure.user.dto.UserResponse;
import com.faketri.market.infastructure.user.gateway.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 *
 * @author Dmitriy Faketri
 */
@RestController
@Log4j2
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Found users.
     *
     * @return the users
     */
    @RequestMapping("/")
    public UserResponse findByLogin(@RequestParam String login) {
        log.info("get user with login - " + login);
        return UserResponse.mapUser(userService.findByLogin(login));
    }

    @RequestMapping("/{id}")
    public UserResponse findById(@RequestParam Long id) {
        return UserResponse.mapUser(userService.findById(id));
    }
}

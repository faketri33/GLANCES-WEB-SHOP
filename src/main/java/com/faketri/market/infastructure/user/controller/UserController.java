package com.faketri.market.infastructure.user.controller;

import com.faketri.market.entity.user.model.Users;
import com.faketri.market.infastructure.user.gateway.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 *
 * @author Dmitriy Faketri
 */
@RestController
@CrossOrigin({ "http://localhost:8081", "http://192.168.1.106:8081/" })
@RequestMapping(path = "/api/user", method = RequestMethod.GET)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Found users.
     *
     * @return the users
     */
    @RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users findByLogin(@RequestParam String login) {
        return userService.findByLogin(login);
    }

    @RequestMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users findById(@RequestParam Long id) {
        return userService.findById(id);
    }

}

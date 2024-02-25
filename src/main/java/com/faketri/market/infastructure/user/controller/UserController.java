package com.faketri.market.infastructure.user.controller;

import com.faketri.market.entity.user.model.Users;
import com.faketri.market.infastructure.user.gateway.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User controller.
 *
 * @author Dmitriy Faketri
 */
@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Found users.
     *
     * @return the users
     */
    @RequestMapping(path = "/", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public Users found() {
        return userService.findByLogin("faketri");
    }

}

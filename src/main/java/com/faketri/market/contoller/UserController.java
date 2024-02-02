package com.faketri.market.contoller;

import com.faketri.market.domain.users.User;
import com.faketri.market.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public User found() {
        return userService.findByLogin("faketri");
    }

}

package com.faketri.market.infastructure.user.controller;

import com.faketri.market.entity.product.model.Product;
import com.faketri.market.entity.user.model.Users;
import com.faketri.market.infastructure.user.dto.UserResponse;
import com.faketri.market.infastructure.user.gateway.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 *
 * @author Dmitriy Faketri
 */
@RestController
@Log4j2
@CrossOrigin({ "http://localhost:8081", "http://192.168.1.106:8081/" })
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Found users.
     *
     * @return the users
     */
    @RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET)
    public UserResponse findByLogin(@RequestParam String login) {
        log.info("get user with login - " + login);
        return UserResponse.mapUser(userService.findByLogin(login));
    }

    @RequestMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET)
    public UserResponse findById(@RequestParam Long id) {
        return UserResponse.mapUser(userService.findById(id));
    }

    @RequestMapping(path = "/like/product", method = RequestMethod.POST)
    public void likeProduct(@RequestBody Product product,
                            @RequestBody Boolean type
    ) {
        Users user = userService.getCurrentUser();

        if (type) user.getFavoriteProduct().add(product);
        else user.getFavoriteProduct().remove(product);

        userService.save(user);
    }

}

package com.faketri.market.infastructure.user.controller;

import com.faketri.market.entity.product.model.Product;
import com.faketri.market.entity.user.model.Users;
import com.faketri.market.infastructure.user.gateway.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The type Post controller.
 *
 * @author Dmitriy Faketri
 */
@RestController
@Log4j2
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
public class PostController {

    private final UserService userService;

    public PostController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/like/product")
    public void likeProduct(@RequestBody Product product,
                            @RequestBody Boolean type
    ) {
        Users user = userService.getCurrentUser();

        if (Boolean.TRUE.equals(type)) user.getFavoriteProduct().add(product);
        else user.getFavoriteProduct().remove(product);

        userService.save(user);
    }
}

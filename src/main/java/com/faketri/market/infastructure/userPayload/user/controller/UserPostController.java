package com.faketri.market.infastructure.userPayload.user.controller;

import com.faketri.market.entity.productPayload.product.model.Product;
import com.faketri.market.entity.productPayload.product.model.ProductItem;
import com.faketri.market.entity.userPayload.user.model.Users;
import com.faketri.market.infastructure.userPayload.user.gateway.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The type Post controller.
 *
 * @author Dmitriy Faketri
 */
@RestController
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
public class UserPostController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    @Autowired
    public UserPostController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/like/product")
    public void likeProduct(@RequestBody Product product) {
        log.info("LIKE PROD " + product.getId());
        Users user = userService.getCurrentUser();
        user.getFavoriteProduct().add(product);
        userService.save(user);
    }

    @RequestMapping("/dislike/product")
    public void dislikeProduct(@RequestBody Product product) {
        log.info("DISLIKE PROD " + product.getId());
        Users user = userService.getCurrentUser();
        user.getFavoriteProduct().remove(product);
        userService.save(user);
    }

    @RequestMapping("/basket/add")
    public void addToBasket(@RequestBody ProductItem product) {
        log.info("ADD BASKET PROD " + product.getId());
        Users user = userService.getCurrentUser();
        user.getBasket().getProducts().add(product);
        userService.save(user);
    }

    @RequestMapping("/basket/remove")
    public void removeFromBasket(@RequestBody ProductItem product) {
        log.info("REMOVE BASKET PROD " + product.getId());
        Users user = userService.getCurrentUser();
        user.getBasket().getProducts().remove(product);
        userService.save(user);
    }
}

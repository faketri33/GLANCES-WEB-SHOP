package com.faketri.market.infastructure.user.payload.user.controller;

import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.user.payload.user.dto.UserUpdateRequest;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/profile/image/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Image updateUserImage(@RequestBody final MultipartFile image) {
        return userService.updateUserImage(image);
    }

    @RequestMapping("/profile/update")
    public void updateUserData(@RequestBody final UserUpdateRequest userUpdateRequest) {
        final Users user = userService.getCurrentUser();

        user.setName(userUpdateRequest.getName());
        user.setSurname(userUpdateRequest.getSurname());
        user.setDateOfBirthday(userUpdateRequest.getDateOfBirthday());
        user.setEmail(userUpdateRequest.getEmail());
        user.setCity(userUpdateRequest.getCity());

        userService.save(user);
    }

    @RequestMapping("/like/product")
    public void likeProduct(@RequestBody final Product product) {
        log.info("LIKE PROD " + product.getId());
        Users user = userService.getCurrentUser();
        user.getFavoriteProduct().add(product);
        userService.save(user);
    }

    @RequestMapping("/dislike/product")
    public void dislikeProduct(@RequestBody final Product product) {
        log.info("DISLIKE PROD " + product.getId());
        Users user = userService.getCurrentUser();
        user.getFavoriteProduct().remove(product);
        userService.save(user);
    }
}

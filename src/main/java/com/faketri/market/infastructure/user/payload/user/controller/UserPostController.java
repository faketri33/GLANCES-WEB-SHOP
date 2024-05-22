package com.faketri.market.infastructure.user.payload.user.controller;

import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.entity.user.payload.order.gateway.mapper.OrderMapper;
import com.faketri.market.entity.user.payload.order.model.Orders;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.user.payload.order.dto.OrdersDto;
import com.faketri.market.infastructure.user.payload.user.dto.UserUpdateRequest;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

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
        final Users user = userService.getCurrentUser();

        final String path = "/app/images/";
        final String name = user.getLogin().replace(' ', '-');

        final String imageName = path + name + "-" + Objects.requireNonNull(image.getOriginalFilename()).replace(' ', '-').toLowerCase();
        System.out.println(imageName);
        try {
            image.transferTo(Paths.get(imageName));
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        user.setProfileImage(new Image(null, imageName));

        return userService.save(user).getProfileImage();
    }

    @RequestMapping("/profile/update")
    public void updateUserData(@RequestBody final UserUpdateRequest userUpdateRequest) {
        final Users user = userService.getCurrentUser();

        user.setName(userUpdateRequest.getFirstName());
        user.setSurname(userUpdateRequest.getLastName());
        user.setDateOfBirthday(userUpdateRequest.getDateOfBirthday());
        user.setEmail(userUpdateRequest.getEmail());

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

    @RequestMapping("/basket/add")
    public void addToBasket(@RequestBody final ProductItem product) {
        log.info("ADD BASKET PROD " + product.getId());
        Users user = userService.getCurrentUser();

        user.getBasket()
                .getProducts()
                .stream()
                .filter(productItem1 -> productItem1.getProduct().equals(product.getProduct()))
                .findAny()
                .ifPresentOrElse(
                        (productItem) -> productItem.setQuantity(productItem.getQuantity()),
                        () -> user.getBasket().getProducts().add(product));

        userService.save(user);
    }

    @RequestMapping("/basket/remove")
    public void removeFromBasket(@RequestBody final Product product) {
        log.info("REMOVE BASKET PROD " + product.getId());
        Users user = userService.getCurrentUser();

        ProductItem productItem = user.getBasket()
                .getProducts()
                .stream()
                .filter(productItems -> productItems.getProduct().equals(product))
                .findFirst()
                .orElseThrow();

        user.getBasket().getProducts().remove(productItem);

        userService.save(user);
    }

    @RequestMapping("/order/create")
    public OrdersDto createOrder(@RequestBody final List<ProductItem> product) {
        Users user = userService.getCurrentUser();
        log.info("CREATE ORDER " + user.getId());

        Orders orders = new Orders();

        orders.getProducts().addAll(product);
        orders.setUsers(user);
        orders.setPrice(
                product
                        .stream().map(p -> p.getProduct().getPrice() * p.getQuantity())
                        .reduce(Integer::sum)
                        .orElse(0)
        );
        user.getOrders().add(orders);

        userService.save(user);

        return OrderMapper.toDto(orders);
    }
}

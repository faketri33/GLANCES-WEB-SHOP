package com.faketri.market.infastructure.user.payload.user.controller;

import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.entity.user.payload.order.gateway.mapper.OrderMapper;
import com.faketri.market.entity.user.payload.order.model.Orders;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.user.payload.order.dto.OrdersDto;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void removeFromBasket(@RequestBody Product product) {
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
    public OrdersDto createOrder(@RequestBody List<ProductItem> product) {
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

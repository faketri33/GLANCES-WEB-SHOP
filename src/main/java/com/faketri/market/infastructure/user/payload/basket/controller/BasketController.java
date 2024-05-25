package com.faketri.market.infastructure.user.payload.basket.controller;

import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.entity.user.payload.basket.model.Basket;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.user.payload.basket.gateway.BasketService;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/basket", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Basket", description = "Operation with Basket")
public class BasketController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @RequestMapping(value = "/add/{basketId}", method = RequestMethod.PUT)
    public Basket addToBasket(@PathVariable final UUID basketId,
                              @RequestParam final UUID productId,
                              @RequestParam final int quantity) {
        log.info("ADD BASKET PROD " + productId);
        return basketService.addProductToBasket(basketId, productId, quantity);
    }

    @RequestMapping(value = "/remove/{basketId}", method = RequestMethod.DELETE)
    public void removeFromBasket(@PathVariable final UUID basketId,
                                 @RequestParam final UUID productId,
                                 @RequestParam final int quantity) {
        log.info("REMOVE BASKET PROD " + productId);
        basketService.removeFromBasket(basketId, productId, quantity);
    }
}

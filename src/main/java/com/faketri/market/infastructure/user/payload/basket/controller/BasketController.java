package com.faketri.market.infastructure.user.payload.basket.controller;


import com.faketri.market.entity.user.payload.basket.model.Basket;
import com.faketri.market.infastructure.user.payload.basket.dto.UpdateQuantityRequest;
import com.faketri.market.infastructure.user.payload.basket.gateway.BasketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/basket")
@Tag(name = "Basket", description = "Operation with Basket")
public class BasketController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @RequestMapping(value = "/update-quantity/{basketId}/{productItemId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Basket updateQuantity(@PathVariable final UUID basketId,
                                 @RequestBody final UpdateQuantityRequest updateQuantityRequest) {
        log.info("updateQuantity: basketId - "
                + basketId + " productItemId - "
                + updateQuantityRequest.getProductItemId() + " quantity - "
                + updateQuantityRequest.getQuantity());
        return basketService.updateQuantity(basketId, updateQuantityRequest.getProductItemId(), updateQuantityRequest.getQuantity());
    }

    @RequestMapping(value = "/add/{basketId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Basket addToBasket(@PathVariable final UUID basketId,
                              @RequestParam final UUID productId) {
        log.info("ADD BASKET PROD " + productId);
        return basketService.addProductToBasket(basketId, productId);
    }

    @RequestMapping(value = "/remove/{basketId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeFromBasket(@PathVariable final UUID basketId,
                                 @RequestParam final UUID productId) {
        log.info("REMOVE BASKET PROD " + productId);
        basketService.removeFromBasket(basketId, productId);
    }
}

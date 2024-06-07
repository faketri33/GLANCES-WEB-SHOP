package com.faketri.market.infastructure.user.payload.basket.gateway;

import com.faketri.market.entity.user.payload.basket.model.Basket;

import java.util.UUID;

public interface BasketService {

    Basket findById(UUID uuid);

    Basket save(Basket basket);

    Basket addProductToBasket(UUID basketId, UUID productId);

    Basket updateQuantity(final UUID basketId, UUID productItemId, final Integer quantity);

    void removeFromBasket(UUID basketId, UUID productId);
}

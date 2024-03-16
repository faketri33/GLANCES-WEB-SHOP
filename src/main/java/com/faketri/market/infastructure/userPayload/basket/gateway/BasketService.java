package com.faketri.market.infastructure.userPayload.basket.gateway;

import com.faketri.market.entity.userPayload.basket.model.Basket;

import java.util.UUID;

public interface BasketService {

    Basket findById(UUID uuid);

    Basket save(Basket basket);
}

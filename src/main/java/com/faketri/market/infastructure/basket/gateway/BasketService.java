package com.faketri.market.infastructure.basket.gateway;

import com.faketri.market.entity.basket.model.Basket;

import java.util.UUID;

public interface BasketService {

    Basket findById(UUID uuid);

    Basket save(Basket basket);
}

package com.faketri.market.usecase.basket;

import com.faketri.market.entity.basket.gateway.BasketRepository;
import com.faketri.market.entity.basket.model.Basket;
import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.infastructure.basket.gateway.BasketService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public Basket findById(UUID uuid) {
        return basketRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Basket not found"));
    }

    @Override
    public Basket save(Basket basket) {
        return basketRepository.save(basket);
    }

}

package com.faketri.market.usecase.shop;

import com.faketri.market.entity.shop.gateway.ShopRepository;
import com.faketri.market.entity.shop.model.Shop;
import com.faketri.market.infastructure.shop.gateway.ShopService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop findById(UUID id) {
        return shopRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Shop> findAll(Pageable pageable) {
        return shopRepository.findAll(pageable);
    }

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public void deleteById(UUID id) {
        shopRepository.deleteById(id);
    }
}

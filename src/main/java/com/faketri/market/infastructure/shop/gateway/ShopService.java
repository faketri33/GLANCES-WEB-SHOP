package com.faketri.market.infastructure.shop.gateway;

import com.faketri.market.entity.shop.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ShopService {

    Shop findById(UUID id);

    Page<Shop> findAll(Pageable pageable);

    Shop save(Shop shop);

    void deleteById(UUID id);
}

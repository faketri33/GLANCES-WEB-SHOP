package com.faketri.market.entity.shop.gateway;

import com.faketri.market.entity.shop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShopRepository extends JpaRepository<Shop, UUID> {
}
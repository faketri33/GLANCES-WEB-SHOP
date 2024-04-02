package com.faketri.market.entity.user.payload.basket.gateway;

import com.faketri.market.entity.user.payload.basket.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BasketRepository extends JpaRepository<Basket, UUID> {
}
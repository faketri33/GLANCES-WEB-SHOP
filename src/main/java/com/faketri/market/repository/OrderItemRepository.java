package com.faketri.market.repository;

import com.faketri.market.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Override
    Optional<OrderItem> findById(Long aLong);

    @Override
    void deleteById(Long aLong);


}
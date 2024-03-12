package com.faketri.market.entity.order.gateway;

import com.faketri.market.entity.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * The interface Order item repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    @Override
    Optional<OrderItem> findById(UUID uuid);

    @Override
    void deleteById(UUID uuid);


}
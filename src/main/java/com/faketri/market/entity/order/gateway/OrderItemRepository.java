package com.faketri.market.entity.order.gateway;

import com.faketri.market.entity.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Order item repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Override
    Optional<OrderItem> findById(Long aLong);

    @Override
    void deleteById(Long aLong);


}
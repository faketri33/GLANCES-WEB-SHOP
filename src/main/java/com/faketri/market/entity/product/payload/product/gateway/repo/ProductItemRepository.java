package com.faketri.market.entity.product.payload.product.gateway.repo;

import com.faketri.market.entity.product.payload.product.model.ProductItem;
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
public interface ProductItemRepository extends JpaRepository<ProductItem, UUID> {

    @Override
    Optional<ProductItem> findById(UUID uuid);

    @Override
    void deleteById(UUID uuid);


}
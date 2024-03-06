package com.faketri.market.entity.promotion.gateway;

import com.faketri.market.entity.promotion.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface Promotion repository.
 *
 * @author Dmitriy Faketri
 */
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    
    Optional<Promotion> findById(UUID aLong);

    @Override
    List<Promotion> findAll();

    @Override
    void deleteById(Long aLong);


}
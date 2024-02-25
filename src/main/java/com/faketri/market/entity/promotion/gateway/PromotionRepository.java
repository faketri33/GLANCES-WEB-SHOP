package com.faketri.market.entity.promotion.gateway;

import com.faketri.market.entity.promotion.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Promotion repository.
 *
 * @author Dmitriy Faketri
 */
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Override
    Optional<Promotion> findById(Long aLong);

    @Override
    List<Promotion> findAll();

    @Override
    void deleteById(Long aLong);


}
package com.faketri.market.repository;

import com.faketri.market.domain.promo.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Override
    Optional<Promotion> findById(Long aLong);

    @Override
    List<Promotion> findAll();

    @Override
    void deleteById(Long aLong);


}
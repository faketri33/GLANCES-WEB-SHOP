package com.faketri.market.infastructure.product.payload.promotion.gateway;

import com.faketri.market.entity.product.payload.promotion.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PromotionService {

    Promotion findById(UUID id);

    List<Promotion> findAll();

    Page<Promotion> findAll(Pageable pageable);

    void isPromotionActive(Promotion promotion);

    Promotion save(Promotion promotion);

}

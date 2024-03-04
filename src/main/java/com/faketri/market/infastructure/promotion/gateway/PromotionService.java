package com.faketri.market.infastructure.promotion.gateway;

import com.faketri.market.entity.promotion.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PromotionService {

    Promotion findById(Long id);

    List<Promotion> findAll();

    Page<Promotion> findAll(Pageable pageable);

    void isPromotionActive(Promotion promotion);

    Promotion save(Promotion promotion);

}

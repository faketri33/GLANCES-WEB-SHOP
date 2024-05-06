package com.faketri.market.infastructure.product.payload.rating.gateway;

import com.faketri.market.entity.product.payload.rating.model.Rating;
import org.hibernate.validator.cfg.defs.UUIDDef;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RatingService {

    Page<Rating> findByIdProduct(UUID id, Pageable pageable);

    Rating save(Rating rating);

    void deleteById(UUID uuid);

}

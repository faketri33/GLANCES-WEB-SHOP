package com.faketri.market.infastructure.productPayload.rating.gateway;

import com.faketri.market.entity.productPayload.rating.model.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RatingService {

    Page<Rating> findByIdProduct(UUID id, Pageable pageable);

    Rating save(Rating rating);

}

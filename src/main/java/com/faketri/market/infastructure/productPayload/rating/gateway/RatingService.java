package com.faketri.market.infastructure.productPayload.rating.gateway;

import com.faketri.market.entity.productPayload.rating.model.Rating;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface RatingService {

    Page<Rating> findByIdProduct(UUID id);

    Rating save(Rating rating);

}

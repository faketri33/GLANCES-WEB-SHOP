package com.faketri.market.infastructure.rating.gateway;

import com.faketri.market.entity.rating.model.Rating;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface RatingService {

    Page<Rating> findByIdProduct(UUID id);

    Rating save(Rating rating);

}

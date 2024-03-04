package com.faketri.market.infastructure.rating.gateway;

import com.faketri.market.entity.rating.model.Rating;
import org.springframework.data.domain.Page;

public interface RatingService {

    Page<Rating> findByIdProduct(Long id);

    Rating save(Rating rating);

}

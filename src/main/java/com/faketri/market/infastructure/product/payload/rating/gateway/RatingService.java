package com.faketri.market.infastructure.product.payload.rating.gateway;

import com.faketri.market.entity.product.payload.rating.model.Rating;
import com.faketri.market.infastructure.product.payload.rating.dto.RatingDto;
import com.faketri.market.infastructure.product.payload.rating.dto.RatingDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RatingService {

    Page<Rating> findByIdProduct(UUID id, Pageable pageable);

    RatingDtoResponse create(RatingDto ratingDto, UUID productId);

    Rating save(Rating rating);

    void deleteById(UUID uuid);
}

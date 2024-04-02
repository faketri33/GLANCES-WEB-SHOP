package com.faketri.market.usecase.product.payload.rating;

import com.faketri.market.entity.product.payload.rating.gateway.RatingRepository;
import com.faketri.market.entity.product.payload.rating.model.Rating;
import com.faketri.market.infastructure.product.payload.rating.gateway.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * The type Rating service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingImpl;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingImpl) {
        this.ratingImpl = ratingImpl;
    }

    /**
     * Find by id product page.
     *
     * @param id the id
     * @return the page
     */
    public Page<Rating> findByIdProduct(UUID id, Pageable pageable) {
        return ratingImpl.findByProduct_Id(id, pageable);
    }

    /**
     * Save rating.
     *
     * @param rating the rating
     * @return the rating
     */
    public Rating save(Rating rating) {
        return ratingImpl.save(rating);
    }

}

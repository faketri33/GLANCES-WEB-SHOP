package com.faketri.market.infastructure.product.gateway;

import com.faketri.market.entity.product.model.Rating;
import com.faketri.market.entity.product.gateway.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * The type Rating service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingImpl;

    /**
     * Find by id product page.
     *
     * @param id the id
     *
     * @return the page
     */
    public Page<Rating> findByIdProduct(Long id) {
        return ratingImpl.findByProduct_Id(id, PageRequest.of(0, 20));
    }

    /**
     * Save rating.
     *
     * @param rating the rating
     *
     * @return the rating
     */
    public Rating save(Rating rating) {
        return ratingImpl.save(rating);
    }

}

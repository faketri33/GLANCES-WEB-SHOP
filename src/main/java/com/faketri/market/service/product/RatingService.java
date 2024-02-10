package com.faketri.market.service.product;

import com.faketri.market.domain.order.Rating;
import com.faketri.market.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingImpl;

    public Page<Rating> findByIdProduct(Long id) {
        return ratingImpl.findByProduct_Id(id, PageRequest.of(0, 20));
    }

    public Rating save(Rating rating) {
        return ratingImpl.save(rating);
    }

}

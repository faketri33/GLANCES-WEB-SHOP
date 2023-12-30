package com.faketri.market.service;

import com.faketri.market.domain.order.Rating;
import com.faketri.market.repository.impl.RatingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingImpl ratingImpl;

    public List<Rating> findByIdProduct(Long id){
        return ratingImpl.findByIdProduct(id);
    }

    public Rating save(Rating rating) {
        return ratingImpl.save(rating);
    }
}

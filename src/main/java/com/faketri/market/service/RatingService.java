package com.faketri.market.service;

import com.faketri.market.entity.Rating;
import com.faketri.market.exception.responseEntity.ResourceNotFoundException;
import com.faketri.market.repository.RatingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingDao ratingDao;

    public Rating findById(Long id){
        return ratingDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Rating with id " + id + " not found")
        );
    }

   public double findAverageOfRatingByProductId(Long productId){
        return ratingDao.averageOfRatingByProductId(productId);
   }

    public void save(Rating rating){ ratingDao.save(rating); }
}

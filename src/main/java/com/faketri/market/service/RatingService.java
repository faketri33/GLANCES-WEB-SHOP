package com.faketri.market.service;

import com.faketri.market.entity.Rating;
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
                () -> new RuntimeException(String.format("Not founded rating by id - %d", id))
        );
    }

    public List<Rating> findByProductId(Long productId){
        return ratingDao.findByProductId(productId);
    }

   public double findAverageOfRatingByProductId(long productId){
        return ratingDao.findAverageOfRatingByProductId(productId);
   }

    public void save(Rating rating){ ratingDao.save(rating); }
}

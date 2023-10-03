package com.faketri.market.contoller;

import com.faketri.market.entity.Rating;
import com.faketri.market.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @RequestMapping(path = "/product/{productId}/RatingList")
    public @ResponseBody List<Rating> getRatings(@PathVariable(value = "productId") Long productId){
        return ratingService.findByProductId(productId);
    }

    @RequestMapping(path = "/product/{productId}")
    public @ResponseBody double getAverage(@PathVariable(value = "productId") Long productId){
        return ratingService.averageOfRatingByProductId(productId);
    }
}

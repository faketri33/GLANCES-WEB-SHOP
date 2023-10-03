package com.faketri.market.contoller;

import com.faketri.market.entity.Rating;
import com.faketri.market.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @RequestMapping(path = "/product/{productId}/RatingList",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Rating> getRatings(@PathVariable(value = "productId") Long productId){
        return ratingService.findByProductId(productId);
    }

   @RequestMapping(path = "/product/{productId}",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
   public @ResponseBody double getAverage(@PathVariable(value = "productId") Long productId){
       return ratingService.findAverageOfRatingByProductId(productId);
   }
}

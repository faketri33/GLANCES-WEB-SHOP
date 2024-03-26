package com.faketri.market.infastructure.productPayload.rating.controller;

import com.faketri.market.entity.productPayload.rating.model.Rating;
import com.faketri.market.infastructure.productPayload.rating.gateway.RatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(value = "/api/rating", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Rating", description = "Operation with product")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/{productId}")
    public Page<Rating> getRatingByProductId(@PathVariable UUID productId,
                                             @RequestParam(name = "page", defaultValue = "1") Integer page,
                                             @RequestParam(name = "size", defaultValue = "20") Integer size) {
        return ratingService.findByIdProduct(productId, PageRequest.of(page, size));
    }
}

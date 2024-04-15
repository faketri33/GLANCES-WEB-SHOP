package com.faketri.market.infastructure.product.payload.rating.controller;

import com.faketri.market.entity.product.payload.rating.model.Rating;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import com.faketri.market.infastructure.product.payload.rating.dto.RatingDto;
import com.faketri.market.infastructure.product.payload.rating.gateway.RatingService;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(value = "/api/rating", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Rating", description = "Operation with product")
public class RatingController {

    private final RatingService ratingService;
    private final UserService userService;
    private final ProductService productService;

    public RatingController(RatingService ratingService, UserService userService, ProductService productService) {
        this.ratingService = ratingService;
        this.userService = userService;
        this.productService = productService;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Page<Rating> getRatingByProductId(@PathVariable UUID productId,
                                             @RequestParam(name = "page", defaultValue = "1") Integer page,
                                             @RequestParam(name = "size", defaultValue = "20") Integer size) {
        return ratingService.findByIdProduct(productId, PageRequest.of(page, size));
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.POST)
    public Rating save(@RequestBody RatingDto ratingDto, @RequestParam UUID productId) {
        Rating rating = new Rating();

        rating.setUsers(userService.getCurrentUser());
        rating.setProduct(productService.findById(productId));
        rating.setDescription(ratingDto.getDescription());
        rating.setGrade(ratingDto.getGrade());

        return ratingService.save(rating);
    }
}

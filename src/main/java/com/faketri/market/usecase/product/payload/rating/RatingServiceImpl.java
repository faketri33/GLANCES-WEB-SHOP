package com.faketri.market.usecase.product.payload.rating;

import com.faketri.market.entity.product.payload.rating.gateway.RatingRepository;
import com.faketri.market.entity.product.payload.rating.model.Rating;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import com.faketri.market.infastructure.product.payload.rating.dto.RatingDto;
import com.faketri.market.infastructure.product.payload.rating.dto.RatingDtoResponse;
import com.faketri.market.infastructure.product.payload.rating.gateway.RatingService;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
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
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingImpl, UserService userService, ProductService productService) {
        this.ratingImpl = ratingImpl;
        this.userService = userService;
        this.productService = productService;
    }

    public Page<Rating> findByIdProduct(UUID id, Pageable pageable) {
        return ratingImpl.findByProduct_Id(id, pageable);
    }

    @Override
    public RatingDtoResponse create(RatingDto ratingDto, UUID productId) {
        Rating rating = new Rating();

        rating.setUsers(userService.getCurrentUser());
        rating.setProduct(productService.findById(productId));
        rating.setDescription(ratingDto.getDescription());
        rating.setGrade(ratingDto.getGrade());

        rating = save(rating);
        return new RatingDtoResponse(
                rating.getId(),
                rating.getDescription(),
                rating.getGrade(),
                rating.getUsers().getProfileImage(),
                rating.getUsers().getLogin()
        );
    }

    public Rating save(Rating rating) {
        return ratingImpl.save(rating);
    }

    @Override
    public void deleteById(UUID uuid) {
        ratingImpl.deleteById(uuid);
    }

}

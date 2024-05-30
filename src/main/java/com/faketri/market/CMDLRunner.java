package com.faketri.market;

import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.payload.brand.model.Brand;
import com.faketri.market.entity.product.payload.categories.model.Categories;
import com.faketri.market.entity.product.payload.characteristics.gateway.CharacteristicsRepository;
import com.faketri.market.entity.product.payload.characteristics.model.Characteristics;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.product.payload.promotion.model.Promotion;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.image.gateway.ImageService;
import com.faketri.market.infastructure.product.payload.brand.gateway.BrandService;
import com.faketri.market.infastructure.product.payload.categories.gateway.CategoriesService;
import com.faketri.market.infastructure.product.payload.characteristics.gateway.CharacteristicsService;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import com.faketri.market.infastructure.product.payload.promotion.gateway.PromotionService;
import com.faketri.market.infastructure.product.payload.rating.gateway.RatingService;
import com.faketri.market.infastructure.user.payload.auth.gateway.AuthService;
import com.faketri.market.infastructure.user.payload.order.gateway.OrderService;
import com.faketri.market.infastructure.user.payload.user.dto.SignUpRequest;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class CMDLRunner {
    public CommandLineRunner commandLineRunner(
            ProductService productService,
            PromotionService promotionService,
            CategoriesService categoriesService, RatingService ratingService,
            UserService userService, BrandService brandService,
            CharacteristicsService characteristicsService,
            ImageService imageService,
            CharacteristicsRepository characteristicsRepository, AuthService authService,
            OrderService orderService) {
        return args -> {

        };
    }
}

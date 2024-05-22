package com.faketri.market;

import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.payload.brand.model.Brand;
import com.faketri.market.entity.product.payload.categories.model.Categories;
import com.faketri.market.entity.product.payload.characteristics.gateway.CharacteristicsRepository;
import com.faketri.market.entity.product.payload.characteristics.model.Characteristics;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.entity.product.payload.promotion.model.Promotion;
import com.faketri.market.entity.user.payload.order.model.Orders;
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
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
            Faker fakerRu = new Faker(new Locale("ru"));
            Faker fakerEn = new Faker();

            List<Categories> categories = new ArrayList<>();
            int i = -1;
            while (i++ < 25)
                categories.add(
                        new Categories(null,
                                fakerRu.commerce().department(),
                                new Image(null, "images/samsung-galaxy-s23.png")
                        ));

            categories = categories.stream().map(categoriesService::save).collect(Collectors.toList());

            List<Brand> brands = new ArrayList<>();
            i = -1;
            while (i++ < 50) brands.add(new Brand(null, fakerEn.company().name()));

            brands = brands.stream().map(brandService::save).collect(Collectors.toList());

            List<Product> products = new ArrayList<>();

            i = -1;
            while (i++ < 1000) {
                products.add(
                        new Product(null,
                                brands.get(i % 50),
                                fakerEn.commerce().productName(),
                                categories.get(i % 25),
                                fakerEn.number().numberBetween(5000, 500000),
                                fakerEn.number().numberBetween(0, 1000),
                                fakerEn.number().numberBetween(0, 100000)
                        )
                );
            }
            products = products.stream().map(product -> {
                product.getImage().addAll(Set.of(
                        new Image(null, "images/testImage/1.png"),
                        new Image(null, "images/testImage/2.png"),
                        new Image(null, "images/testImage/3.png")
                ));
                product.setDescription(fakerRu.lorem().sentence(155));
                product.getCharacteristics().addAll(Set.of(
                        new Characteristics(null, "Цвет", fakerRu.color().name()),
                        new Characteristics(null, "Материал", fakerRu.commerce().material()),
                        new Characteristics(null, "Вес", fakerRu.number().randomDigitNotZero() + " кг"),
                        new Characteristics(null, "Размер", fakerRu.random().nextInt(20, 50) + " см")
                ));
                return productService.save(product);
            }).collect(Collectors.toList());

            Promotion promotion = new Promotion();
            promotion.setBanner(new Image(null, "/images/promo-cover_L.png"));
            promotion.setDateOfStart(LocalDate.now());
            promotion.setTitle("ПРОДАМ ВСЕ И ВСЕМ АУКЦИЯ");
            promotion.setDescription(fakerRu.lorem().paragraph(25));
            promotion.setDateOfEnd(LocalDate.of(2024, 10, 20));
            promotion.getPromotionProductItems().addAll(
                    List.of(products.get(0), products.get(1), products.get(2), products.get(3),
                            products.get(4), products.get(5), products.get(6), products.get(7))
            );
            promotionService.save(promotion);


            Users user = new Users();

            user.setLogin("testerovkaNew");
            user.setEmail("test@testNew");
            user.setPassword("123123123");

            authService.signUp(
                    new SignUpRequest(user.getLogin(),
                            user.getEmail(),
                            user.getPassword())
            );

        };
    }
}

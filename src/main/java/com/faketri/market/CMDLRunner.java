package com.faketri.market;

import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.gateway.repo.child.CharacteristicsRepository;
import com.faketri.market.entity.product.model.Product;
import com.faketri.market.entity.product.model.child.Brand;
import com.faketri.market.entity.product.model.child.Categories;
import com.faketri.market.entity.product.model.child.Characteristics;
import com.faketri.market.infastructure.brand.gateway.BrandService;
import com.faketri.market.infastructure.categories.gateway.CategoriesService;
import com.faketri.market.infastructure.characteristics.gateway.CharacteristicsService;
import com.faketri.market.infastructure.image.gateway.ImageService;
import com.faketri.market.infastructure.product.gateway.ProductService;
import com.faketri.market.infastructure.promotion.gateway.PromotionService;
import com.faketri.market.infastructure.rating.gateway.RatingService;
import com.faketri.market.infastructure.user.gateway.UserService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CMDLRunner {

    @Bean
    public CommandLineRunner commandLineRunner(
            ProductService productService,
            PromotionService promotionService,
            CategoriesService categoriesService, RatingService ratingService,
            UserService userService, BrandService brandService,
            CharacteristicsService characteristicsService,
            ImageService imageService,
            CharacteristicsRepository characteristicsRepository) {
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

            Set<Characteristics> characteristics = Set.of(
                    new Characteristics(null, "Цвет", fakerRu.color().name()),
                    new Characteristics(null, "Материал", fakerRu.commerce().material()),
                    new Characteristics(null, "Вес", fakerRu.number().randomDigitNotZero() + " кг"),
                    new Characteristics(null, "Размер", fakerRu.random().nextInt(20, 50) + " см")
            ).stream().map(characteristicsService::save).collect(Collectors.toSet());

            i = -1;
            while (i++ < 1000) {
                products.add(
                        new Product(null,
                                brands.get(i % 50),
                                fakerEn.commerce().productName(),
                                categories.get(i % 25),
                                BigDecimal.valueOf(fakerEn.number().numberBetween(15000, 500000)),
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
                product.getCharacteristics().addAll(characteristics);
                return productService.save(product);
            }).collect(Collectors.toList());
        };
    }
}

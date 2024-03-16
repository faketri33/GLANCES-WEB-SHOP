package com.faketri.market;

import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.productPayload.brand.model.Brand;
import com.faketri.market.entity.productPayload.categories.model.Categories;
import com.faketri.market.entity.productPayload.characteristics.gateway.CharacteristicsRepository;
import com.faketri.market.entity.productPayload.characteristics.model.Characteristics;
import com.faketri.market.entity.productPayload.product.model.Product;
import com.faketri.market.entity.productPayload.product.model.ProductItem;
import com.faketri.market.entity.productPayload.promotion.model.Promotion;
import com.faketri.market.entity.userPayload.order.model.Orders;
import com.faketri.market.entity.userPayload.user.gateway.mapper.UserMapper;
import com.faketri.market.entity.userPayload.user.model.Users;
import com.faketri.market.infastructure.config.web.authentication.gateway.AuthService;
import com.faketri.market.infastructure.image.gateway.ImageService;
import com.faketri.market.infastructure.productPayload.brand.gateway.BrandService;
import com.faketri.market.infastructure.productPayload.categories.gateway.CategoriesService;
import com.faketri.market.infastructure.productPayload.characteristics.gateway.CharacteristicsService;
import com.faketri.market.infastructure.productPayload.product.gateway.ProductService;
import com.faketri.market.infastructure.productPayload.promotion.gateway.PromotionService;
import com.faketri.market.infastructure.productPayload.rating.gateway.RatingService;
import com.faketri.market.infastructure.userPayload.order.gateway.OrderService;
import com.faketri.market.infastructure.userPayload.user.dto.SignUpRequest;
import com.faketri.market.infastructure.userPayload.user.gateway.UserService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
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
                product.getCharacteristics().addAll(Set.of(
                        new Characteristics(null, "Цвет", fakerRu.color().name()),
                        new Characteristics(null, "Материал", fakerRu.commerce().material()),
                        new Characteristics(null, "Вес", fakerRu.number().randomDigitNotZero() + " кг"),
                        new Characteristics(null, "Размер", fakerRu.random().nextInt(20, 50) + " см")
                ));
                return productService.save(product);
            }).collect(Collectors.toList());

            Promotion promotion = new Promotion();
            promotion.setDateOfStart(LocalDateTime.now());
            promotion.setTitle("ПРОДАМ ВСЕ И ВСЕМ АУКЦИЯ");
            promotion.setDescription(fakerRu.lorem().paragraph(25));
            promotion.setDateOfEnd(LocalDateTime.of(2024, 10, 20, 0, 0));
            promotion.getPromotionProductItems().addAll(
                    List.of(products.get(0), products.get(1), products.get(2), products.get(3),
                            products.get(4), products.get(5), products.get(6), products.get(7))
            );
            promotionService.save(promotion);


            Users user = new Users();

            user.setLogin("testerovka");
            user.setEmail("test@test");
            user.setPassword("123123123");

            user = UserMapper.toObj(authService.signUp(
                    new SignUpRequest(user.getLogin(),
                            user.getEmail(),
                            user.getPassword())
            ).getUser());

            Orders orders = new Orders();

            orders.getProducts().add(new ProductItem(null, products.get(0), 1));
            orders.getProducts().add(new ProductItem(null, products.get(23), 4));
            orders.setUsers(user);

            orderService.save(orders);
        };
    }
}

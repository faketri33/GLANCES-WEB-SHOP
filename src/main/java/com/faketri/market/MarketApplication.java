package com.faketri.market;

import com.faketri.market.service.product.*;
import com.faketri.market.service.promo.PromotionService;
import com.faketri.market.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketApplication {

    static final Logger log = LoggerFactory.getLogger(MarketApplication.class);

    public static void main(String[] args) {
        log.info("Before Starting application");
        SpringApplication.run(MarketApplication.class, args);
        log.debug("Starting my application in debug with {} args", args.length);
        log.info("Starting my application with {} args.", args.length);
    }

    public CommandLineRunner commandLineRunner(ProductService productService,
                                               PromotionService promotionService,
                                               CategoriesService categoriesService,
                                               RatingService ratingService,
                                               UserService userService,
                                               BrandService brandService,
                                               CharacteristicsService characteristicsService,
                                               ImageService imageService
    ) {
        return args -> {

            /*Categories categories = categoriesService.save(
                    new Categories(null, "Смартфоны", Files.readAllBytes(
                            Path.of("D:\\rolll\\IdeaProjects\\OnlineMarket\\assets\\Mask group.png"))));
            Brand Samsung = brandService.save(new Brand(null, "Samsung"));
            Brand Apple = brandService.save(new Brand(null, "Apple"));
            Brand Nothing = brandService.save(new Brand(null, "Nothing"));

            List<Product> products = new ArrayList<>();

            products.add(
                    new Product(null, Samsung, "S23", categories, 23000L, false,
                                0L, 0, 100, 20
                    ));

            products.add(
                    new Product(null, Apple, "14", categories, 23000L, false,
                                0L, 0, 100, 20
                    ));

            products.add(
                    new Product(null, Nothing, "Phone 2", categories, 23000L,
                                false, 0L, 0, 100, 20
                    ));

            List<Characteristics> characteristics = new ArrayList<>();

            characteristics.add(characteristicsService.save(
                    new Characteristics(null, "Диагональ экрана", "6.5")));
            characteristics.add(characteristicsService.save(
                    new Characteristics(null, "Диагональ экрана", "6.7")));
            characteristics.add(characteristicsService.save(
                    new Characteristics(null, "Аккумулятор", "4500мач")));
            characteristics.add(characteristicsService.save(
                    new Characteristics(null, "Аккумулятор", "3500мач")));
            characteristics.add(characteristicsService.save(
                    new Characteristics(null, "Аккумулятор", "3700мач")));

            products.get(1).getImage().add(imageService.save(new Image(new File(
                    "D:\\rolll\\IdeaProjects\\OnlineMarket\\assets\\samsung-galaxy-s23.png"))));
            products.get(2).getImage().add(imageService.save(new Image(new File(
                    "D:\\rolll\\IdeaProjects\\OnlineMarket\\assets\\iphone-14.png"))));
            products.get(0).getImage().add(imageService.save(new Image(new File(
                    "D:\\rolll\\IdeaProjects\\OnlineMarket\\assets\\nothing-2.png"))));

            products.get(0).getCharacteristics().addAll(
                    List.of(characteristics.get(0), characteristics.get(2)));
            products.get(1).getCharacteristics().addAll(
                    List.of(characteristics.get(1), characteristics.get(3)));
            products.get(2).getCharacteristics().addAll(
                    List.of(characteristics.get(1), characteristics.get(4)));

            products.forEach(productService::save);

            Promotion promotion = new Promotion(null, Files.readAllBytes(
                    Path.of("D:\\rolll\\IdeaProjects\\OnlineMarket\\assets\\promo-cover_L.png")),
                                                "Скидки на флагманские смартфоны",
                                                "Не пропусти недели флагманских смартфонов в GLANCES " + "Покупай топовые модели брендов с Супервыгодой — выбирай уже сейчас на сайте, в приложении и в магазинах сети.",
                                                LocalDateTime.of(2023, 12, 20,
                                                                 0, 0
                                                ),
                                                LocalDateTime.of(2024, 2, 5, 0,
                                                                 0
                                                )
            );

            List<Product> productList = productService.findAll();
            productList.forEach(item -> item.setDiscount(10));
            promotion.getPromotionItems().addAll(productList);
            promotionService.save(promotion);*/
        };
    }

}


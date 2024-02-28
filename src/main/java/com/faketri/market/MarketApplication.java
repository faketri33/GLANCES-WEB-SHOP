package com.faketri.market;

import com.faketri.market.infastructure.image.gateway.ImageService;
import com.faketri.market.infastructure.product.gateway.ProductService;
import com.faketri.market.infastructure.product.gateway.child.BrandService;
import com.faketri.market.infastructure.product.gateway.child.CategoriesService;
import com.faketri.market.infastructure.product.gateway.child.CharacteristicsService;
import com.faketri.market.infastructure.promotion.gateway.PromotionService;
import com.faketri.market.infastructure.rating.gateway.RatingService;
import com.faketri.market.infastructure.user.gateway.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The type Market application.
 *
 * @author Dmitriy Faketri
 */
@SpringBootApplication
public class MarketApplication {

    /**
     * The Log.
     */
    static final Logger log = LoggerFactory.getLogger(MarketApplication.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        log.info("Before Starting application");
        SpringApplication.run(MarketApplication.class, args);
        log.debug("Starting my application in debug with {} args", args.length);
        log.info("Starting my application with {} args.", args.length);
    }

    /**
     * Command line runner command line runner.
     *
     * @param productService         the product service
     * @param promotionService       the promotion service
     * @param categoriesService      the categories service
     * @param ratingService          the rating service
     * @param userService            the user service
     * @param brandService           the brand service
     * @param characteristicsService the characteristics service
     * @param imageService           the image service
     *
     * @return the command line runner
     */
    @Bean
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
            /*Categories categories = categoriesService.save(new Categories(null,
                                                                          "Холодильники",
                                                                          imageService.save(
                                                                                  new Image(
                                                                                          null,
                                                                                          "images/pingvinus.png"
                                                                                  ))
            ));

            Categories smartphone = categoriesService.save(new Categories(null,
                                                                          "Смартфоны",
                                                                          imageService.save(
                                                                                  new Image(
                                                                                          null,
                                                                                          "images/Mask-group.png"
                                                                                  ))
            ));

            List<Product> products = new ArrayList<>();

            Product product = new Product(null,
                                          brandService.save(new Brand(null,
                                                                      "Samsung"
                                          )),
                                          "RB30A30N0WW/WT",
                                          categories,
                                          46999L,
                                          10,
                                          0
            );

            product.getImage().addAll(List.of(
                    imageService.save(new Image(null, "images/hol.png")),
                    imageService.save(new Image(null, "images/hol1.png"))
            ));

            product.getCharacteristics().addAll(List.of(
                    characteristicsService.save(new Characteristics(null,
                                                                    "Общий объем",
                                                                    "332л"
                    )),
                    characteristicsService.save(new Characteristics(null,
                                                                    "Количество компрессоров",
                                                                    "1"
                    )),
                    characteristicsService.save(new Characteristics(null,
                                                                    "Система размораживания морозильной камеры",
                                                                    "No Frost"
                    ))
            ));

            products.add(new Product(null,
                                     brandService.save(new Brand(null,
                                                                 "Samsung"
                                     )),
                                     "S24",
                                     smartphone,
                                     184000L,
                                     100,
                                     20
            ));
            products.add(new Product(null,
                                     brandService.save(new Brand(null,
                                                                 "Apple"
                                     )),
                                     "Pro Max 14",
                                     smartphone,
                                     154000L,
                                     230,
                                     1240
            ));

            products.get(0)
                    .getImage()
                    .add(imageService.save(new Image(null,
                                                     "images/samsung-galaxy-s23.png"
                    )));
            products.get(0).getCharacteristics().addAll(List.of(
                    characteristicsService.save(new Characteristics(null,
                                                                    "Диагональ экрана",
                                                                    "6.7\""
                    )),
                    characteristicsService.save(new Characteristics(null,
                                                                    "Аккумулятор",
                                                                    "5500 мА*ч"
                    ))
            ));

            products.get(1)
                    .getImage()
                    .add(imageService.save(new Image(null,
                                                     "images/iphone-14.png"
                    )));
            products.get(1).getCharacteristics().addAll(List.of(
                    characteristicsService.save(new Characteristics(null,
                                                                    "Диагональ экрана",
                                                                    "6.1"
                    )),
                    characteristicsService.save(new Characteristics(null,
                                                                    "Аккумулятор",
                                                                    "3279 мА*ч"
                    ))
            ));

            productService.save(product);
            products.forEach(productService::save);*/
        };
    }

}


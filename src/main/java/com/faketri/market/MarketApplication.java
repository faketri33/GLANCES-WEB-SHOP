package com.faketri.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner commandLineRunner
			(ProductService productService, PromotionService promotionService,
			 CategoriesService categoriesService, RatingService ratingService, UserService userService,
			 BrandService brandService, CharacteristicsService characteristicsService,
			 ImageService imageService){
		return args -> {

			Categories categories = categoriesService.save(new Categories(null, "phone"));
			Brand Samsung = brandService.save(new Brand(null, "Samsung"));
			Brand Apple = brandService.save(new Brand(null, "Apple"));
			Brand Nothing = brandService.save(new Brand(null, "Nothing"));

			List<Product> products = new ArrayList<>();

			products.add(
					new Product(null,
						Samsung,
						"S23",
						categories,
						23000L,
						false,
						0L,
						0,
						100,
						20)
			);

			products.add(
					new Product(null,
							Apple,
							"14",
							categories,
							23000L,
							false,
							0L,
							0,
							100,
							20)
			);

			products.add(
					new Product(null,
							Nothing,
							"Phone 2",
							categories,
							23000L,
							false,
							0L,
							0,
							100,
							20)
			);

			List<Characteristics> characteristics = new ArrayList<>();

			characteristics.add(characteristicsService.save(new Characteristics(null, "Диагональ экрана", "6.5")));
			characteristics.add(characteristicsService.save(new Characteristics(null, "Диагональ экрана", "6.7")));
			characteristics.add(characteristicsService.save(new Characteristics(null, "Аккумулятор", "4500мач")));
			characteristics.add(characteristicsService.save(new Characteristics(null, "Аккумулятор", "3500мач")));
			characteristics.add(characteristicsService.save(new Characteristics(null, "Аккумулятор", "3700мач")));

			products.get(0).getImage().add(imageService.save(new Image(new File("/home/faketri/Изображения/samsung-galaxy-s23-ultra-transparent-image-free-png.png"))));
			products.get(1).getImage().add(imageService.save(new Image(new File("/home/faketri/Изображения/png-transparent-iphone-14.png"))));
			products.get(2).getImage().add(imageService.save(new Image(new File("/home/faketri/Изображения/ovyn7iw2qktuk2lnt1r9h9mfbjn98qmx.png"))));

			products.get(0).getCharacteristics().addAll(List.of(characteristics.get(0), characteristics.get(2)));
			products.get(1).getCharacteristics().addAll(List.of(characteristics.get(1), characteristics.get(3)));
			products.get(2).getCharacteristics().addAll(List.of(characteristics.get(1), characteristics.get(4)));

			products.forEach(productService::save);

			Promotion promotion = new Promotion(null, Files.readAllBytes(Path.of("/home/faketri/Изображения/41fc27b6bc19c64da979badccb18f5a5.jpg")), "РАСПРОДАЖА",
					"Не пропустите Главное событие года в ГОДУУУУ!" +
							"Мегаскидки и кешбэк на технику, товары для дома, аксессуары и многое другое. " +
							"А ещё шанс получить главные призы — машину и квартиру в Москве. Участвуйте в розыгрыше уже сейчас!",
					LocalDateTime.of(2023, 12,20, 12,0),
					LocalDateTime.of(2023, 12,30, 12, 0));

			List<Product> productList = productService.findAll();
			promotion.getPromotionItems().addAll(productList);
			promotionService.save(promotion);
		};
	}*/
}


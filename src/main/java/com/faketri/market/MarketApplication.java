package com.faketri.market;

import com.faketri.market.entity.*;
import com.faketri.market.service.BrandService;
import com.faketri.market.service.CategoriesService;
import com.faketri.market.service.ProductService;
import com.faketri.market.service.PromotionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ProductService productService, PromotionService promotionService){
		return args -> {
			Promotion promotion = new Promotion(null, Files.readAllBytes(Path.of("C:\\Users\\rolll\\Downloads\\14fa9860cff53648c5543a262859347f.jpg")), "РАСПРОДАЖА",
					"Не пропустите Главное событие года в ГОДУУУУ!" +
							"Мегаскидки и кешбэк на технику, товары для дома, аксессуары и многое другое. " +
							"А ещё шанс получить главные призы — машину и квартиру в Москве. Участвуйте в розыгрыше уже сейчас!",
					LocalDateTime.of(2023, 12,20, 12,00),
					LocalDateTime.of(2023, 12,30, 12,00));

			List<Product> productList = productService.findAll();
			promotion.getProducts().put(10, productList);
			promotionService.save(promotion);
		};
	}
}


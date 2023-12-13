package com.faketri.market;

import com.faketri.market.entity.*;
import com.faketri.market.service.BrandService;
import com.faketri.market.service.CategoriesService;
import com.faketri.market.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ProductService productService, BrandService brandService, CategoriesService categoriesService){
		return args -> {
			Product product = new Product(null,
					new Brand(null, "apple"),
					"15",
					140000L,
					100,
					23
			);
			product.getCharacteristics().addAll(List.of(new Characteristics[]{
                    new Characteristics(null, "Диагональ экрана", "14"),
                    new Characteristics(null, "Тип матрицы", "ips")
            }));
			Categories cat = new Categories(null, "Смартфоны");
			cat.setId(categoriesService.save(cat));
			product.setCategories(cat);
			product.getImage().add(new Image(new File("C:\\Users\\rolll\\Downloads\\EXag993PwjE.jpg")));
			productService.save(
					product
			);
		};
	}
}

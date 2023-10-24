package com.faketri.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(EBrand.values()));
		SpringApplication.run(MarketApplication.class, args);

	}
}

package com.faketri.market.contoller;

import com.faketri.market.entity.Product;
import com.faketri.market.entity.Rating;
import com.faketri.market.entity.enums.EBrand;
import com.faketri.market.entity.enums.ECategories;
import com.faketri.market.service.ProductService;
import com.faketri.market.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private RatingService ratingService;

    @RequestMapping(path = "/")
    public void welcome(){

        Rating rating = new Rating();
        rating.setGrade((short) 3.5);
        rating.setDescription("Shit");

        ratingService.save(rating);

        Product product = new Product();

        product.setBrand(EBrand.SAMSUNG);
        product.setName_model("S23 ULTRA");
        product.setPrice(24000);
        product.setCategories(Set.of(ECategories.PHONE));
        product.getRating().add(rating);

        productService.save(product);

    }

    @RequestMapping(path = "/product",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Product> getAllProduct(){
        return productService.findAll();
    }

    @RequestMapping(path = "/product/brandName",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody EBrand[] getAllProductBrandName(){
        return EBrand.values();
    }

    @RequestMapping(path = "/product/{productId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Product getProduct(@PathVariable(value = "productId") Long productId){
        return productService.findById(productId);
    }
}

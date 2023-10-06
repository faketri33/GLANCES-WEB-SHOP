package com.faketri.market.contoller;

import com.faketri.market.entity.Product;
import com.faketri.market.entity.enums.EBrand;
import com.faketri.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Product> getAllProduct(){
        return productService.findAll();
    }

    @RequestMapping(path = "/brandName",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody EBrand[] getAllProductBrandName(){
        return EBrand.values();
    }

    @RequestMapping(path = "/{productId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Product getProduct(@PathVariable(value = "productId") Long productId){
        return productService.findById(productId);
    }

    @RequestMapping(path = "/pages/{index}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Product> getProductFromIndex(@PathVariable(value = "index") Long index){
        return productService.getRecordsFromIndex(index);
    }
}

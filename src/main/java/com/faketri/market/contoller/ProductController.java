package com.faketri.market.contoller;


import com.faketri.market.domain.product.Product;
import com.faketri.market.service.product.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/product")
@Tag(name = "Product", description = "Operation with product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Product> getAll(){
        return productService.findAll();
    }
    @RequestMapping(path = "/categories/{categoriesId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Product> getByCategories(@PathVariable(value = "categoriesId") Long categoriesId){
        return productService.findByCategories(categoriesId);
    }
    @RequestMapping(path = "/product",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Page<Product> getAll(
            @RequestParam("number") Integer page_number,
            @RequestParam("size") Integer page_size){
        return productService.findAll(PageRequest.of(page_number, page_size));
    }
    @RequestMapping(path = "/{productId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Product getProduct(@PathVariable(value = "productId") Long productId){
        return productService.findById(productId);
    }
    @RequestMapping(path = "/top-selling",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Page<Product> getTopSelling(
            @RequestParam("number") Integer page_number,
            @RequestParam("size") Integer page_size){
        return productService.findTopSelling(PageRequest.of(page_number, page_size));
    }
    @RequestMapping(path = "/save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody Product product){
        productService.save(product);
    }
    @RequestMapping(path = "/update",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Product product){
        productService.update(product);
    }
    @RequestMapping(path = "/delete",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody Product product){
        productService.delete(product);
    }
}

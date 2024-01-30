package com.faketri.market.contoller;


import com.faketri.market.domain.product.Product;
import com.faketri.market.service.product.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

/*    @RequestMapping(path = "/categories/{categoriesId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Product> getByCategories(@PathVariable(value = "categoriesId") Long categoriesId){
        return productService.findByCategories(categoriesId);
    }*/
    /**
     * REST service endpoint - '/categories/{categoriesId}?number=1&size=20'
     * @param categoriesId id categories,
     * @param page_number number of page product
     * @param page_size count item on page
     * @return Page products in categories
     * */
    @RequestMapping(path = "/categories/{categoriesId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Page<Product> getByCategories(@PathVariable(value = "categoriesId") Long categoriesId,
                                                       @RequestParam(name = "number", required = true, defaultValue = "1")
                                                       Integer page_number,
                                                       @RequestParam(name ="size", required = true, defaultValue = "20")
                                                       Integer page_size){
        System.out.println("HI");
        return productService.findByCategories(categoriesId, PageRequest.of(page_number, page_size));
    }
    /**
     * REST service endpoint - '/product?number=1&size=20'
     * @param page_number number of page product
     * @param page_size count item on page
     * @return Page Product
     * */
    @RequestMapping(path = "/product",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Page<Product> getAll(
            @RequestParam(name = "number", required = true, defaultValue = "1")
            Integer page_number,
            @RequestParam(name ="size", required = true, defaultValue = "20")
            Integer page_size){
        return productService.findAll(PageRequest.of(page_number, page_size));
    }
    /**
     * REST service endpoint
     * @param productId id product
     * @return product with productId
     * */
    @RequestMapping(path = "/{productId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Product getProduct(@PathVariable(value = "productId") Long productId){
        return productService.findById(productId);
    }

    /**
     * REST service endpoint
     * @param page_number number of page
     * @param page_size count item on page
     * @return top-selling product
     * */
    @RequestMapping(path = "/top-selling",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Page<Product> getTopSelling(
            @RequestParam(name = "number", required = true, defaultValue = "1")
            Integer page_number,
            @RequestParam(name ="size", required = true, defaultValue = "20")
            Integer page_size){
        return productService.findTopSelling(PageRequest.of(page_number, page_size));
    }
    /**
     * REST service endpoint
     * Save product
     * @param product Object Product
     * */
    @RequestMapping(path = "/save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody Product product){
        productService.save(product);
    }
    /**
     * REST service endpoint
     * Update product
     * @param product Object Product
     * */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Product product){
        productService.update(product);
    }
    /**
     * REST service endpoint
     * Delete product
     * @param product Object Product
     * */
    @RequestMapping(path = "/delete",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody Product product){
        productService.delete(product);
    }
}

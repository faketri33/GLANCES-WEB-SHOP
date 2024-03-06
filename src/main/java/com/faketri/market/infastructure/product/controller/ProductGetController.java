package com.faketri.market.infastructure.product.controller;

import com.faketri.market.entity.product.model.Product;
import com.faketri.market.infastructure.product.gateway.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The type Product controller.
 *
 * @author Dmitriy Faketri
 */
@RestController()
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(value = "/api/product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Product", description = "Operation with product")
public class ProductGetController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ProductService productService;

    public ProductGetController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * REST get all product
     *
     * @return Collection of product
     */
    @RequestMapping("/")
    public List<Product> getAll() {
        return productService.findAll();
    }

    /**
     * REST service endpoint - '/categories/{categoriesId}?number=1&size=20'
     *
     * @param categoriesId id categories,
     * @param pageNumber   number of page product
     * @param pageSize     count item on page
     * @return Page products in categories
     */
    @RequestMapping("/categories/{categoriesId}")
    public Page<Product> getByCategories(
            @PathVariable(value = "categoriesId") UUID categoriesId,
            @RequestParam(name = "number", required = true,
                    defaultValue = "1") Integer pageNumber,
            @RequestParam(name = "size", required = true,
                    defaultValue = "20") Integer pageSize
    ) {
        return productService.findByCategories(categoriesId,
                PageRequest.of(pageNumber, pageSize)
        );
    }

    /**
     * Gets promotion product.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the promotion product
     */
    @RequestMapping("/promotion/")
    public Page<Product> getPromotionProduct(
            @RequestParam(name = "number", required = true,
                    defaultValue = "1") Integer pageNumber,
            @RequestParam(name = "size", required = true,
                    defaultValue = "20") Integer pageSize
    ) {
        return productService.findPromotionProduct(PageRequest.of(pageNumber, pageSize));
    }

    /**
     * REST service endpoint - '/product?number=1&size=20'
     *
     * @param pageNumber number of page product
     * @param pageSize   count item on page
     * @return Page Product
     */
    @RequestMapping("/product")
    public Page<Product> getAll(
            @RequestParam(name = "number", required = true,
                    defaultValue = "1") Integer pageNumber,
            @RequestParam(name = "size", required = true,
                    defaultValue = "20") Integer pageSize
    ) {
        return productService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    /**
     * REST service endpoint
     *
     * @param productId id product
     * @return product with productId
     */
    @RequestMapping("/{productId}")
    public Product getProduct(
            @PathVariable(value = "productId") UUID productId
    ) {
        return productService.findById(productId);
    }

    /**
     * REST service endpoint
     *
     * @param pageNumber number of page
     * @param pageSize   count item on page
     * @return top-selling product
     */
    @RequestMapping(path = "/top-selling")
    public Page<Product> getTopSelling(
            @RequestParam(name = "number", required = true,
                    defaultValue = "1") Integer pageNumber,
            @RequestParam(name = "size", required = true,
                    defaultValue = "20") Integer pageSize
    ) {
        return productService.findTopSelling(PageRequest.of(pageNumber, pageSize));
    }

}

package com.faketri.market.infastructure.product.payload.product.controller;

import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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

    @Autowired
    public ProductGetController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/max-price")
    public Integer findMaxPrice() {
        log.info("max-price");
        return productService.findMaxPrice();
    }

    @RequestMapping("/promotion")
    public Page<Product> findPromotionProduct(
            @RequestParam(name = "number", required = true,
                    defaultValue = "1") final Integer pageNumber,
            @RequestParam(name = "size", required = true,
                    defaultValue = "20") final Integer pageSize
    ) {
        return productService.findPromotionProduct(PageRequest.of(pageNumber, pageSize));
    }

    @RequestMapping("/product")
    public Page<Product> findAll(
            @RequestParam(name = "number", required = true,
                    defaultValue = "1") final Integer pageNumber,
            @RequestParam(name = "size", required = true,
                    defaultValue = "20") final Integer pageSize
    ) {
        return productService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @RequestMapping("/{productId}")
    public Product findProductById(
            @PathVariable(value = "productId") final UUID productId
    ) {
        return productService.findById(productId);
    }

    @RequestMapping(path = "/top-selling")
    public Page<Product> findTopSelling(
            @RequestParam(name = "number", required = true,
                    defaultValue = "0") final Integer pageNumber,
            @RequestParam(name = "size", required = true,
                    defaultValue = "20") final Integer pageSize
    ) {
        return productService.findTopSelling(PageRequest.of(pageNumber, pageSize));
    }

    @RequestMapping("/search")
    public Page<Product> findBySearchParam(
            @RequestParam(name = "number", required = true,
                    defaultValue = "0") final Integer pageNumber,
            @RequestParam(name = "size", required = true,
                    defaultValue = "20") final Integer pageSize,
            @RequestParam(value = "minPrice", required = false) final Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) final Integer maxPrice,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "categories", required = false) final UUID categoriesId,
            @RequestParam(value = "characteristics", required = false) final List<UUID> filter
    ) {
        name = URLDecoder.decode(name, StandardCharsets.UTF_8);
        log.info("getByFilter: " + String.format("Name product - %s , categories id - %s", name, categoriesId));
        return productService.findBySearchParam(PageRequest.of(pageNumber, pageSize), minPrice, maxPrice, name, filter, categoriesId);

    }

}

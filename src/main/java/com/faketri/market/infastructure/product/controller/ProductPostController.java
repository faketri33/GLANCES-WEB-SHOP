package com.faketri.market.infastructure.product.controller;


import com.faketri.market.entity.product.model.Product;
import com.faketri.market.entity.product.model.child.Characteristics;
import com.faketri.market.infastructure.product.gateway.ProductService;
import com.faketri.market.usecase.product.ProductServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The type Product controller.
 *
 * @author Dmitriy Faketri
 */
@RestController()
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(value = "/api/product", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Product", description = "Operation with product")
public class ProductPostController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ProductService productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    public ProductPostController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    /**
     * Gets by filter.
     *
     * @param categoriesId the categories id
     * @param pageNumber   the page number
     * @param pageSize     the page size
     * @param filter       the filter
     * @return the by filter
     */
    @RequestMapping("/categories/{categoriesId}")
    public Page<Product> getByFilter(
            @PathVariable(value = "categoriesId") UUID categoriesId,
            @RequestParam(name = "number", required = true,
                    defaultValue = "1") Integer pageNumber,
            @RequestParam(name = "size", required = true,
                    defaultValue = "20") Integer pageSize,
            @RequestBody() List<Characteristics> filter
    ) {
        log.info("Get product with filer, filter : " + filter.stream()
                .map(item -> item.getName() + ": " + item.getValue())
                .collect(Collectors.joining(
                        ", ")));

        return productService.findByCategoriesFilteredCharacteristics(PageRequest.of(pageNumber, pageSize),
                categoriesId,
                filter
        );
    }

    /**
     * REST service endpoint
     * Save product
     *
     * @param product Object Product
     */
    @RequestMapping("/save")
    public void save(@RequestBody Product product) {
        productService.save(product);
    }

    /**
     * REST service endpoint
     * Update product
     *
     * @param product Object Product
     */
    @RequestMapping("/update")
    public void update(@RequestBody Product product) {
        productService.save(product);
    }

    /**
     * REST service endpoint
     * Delete product
     *
     * @param product Object Product
     */
    @RequestMapping("/delete")
    public void delete(@RequestBody Product product) {
        productService.delete(product);
    }

}

package com.faketri.market.infastructure.product.controller;


import com.faketri.market.entity.product.model.Product;
import com.faketri.market.entity.product.model.child.Characteristics;
import com.faketri.market.infastructure.product.dto.CharacteristicsRequest;
import com.faketri.market.infastructure.product.gateway.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Product controller.
 *
 * @author Dmitriy Faketri
 */
@RestController()
@CrossOrigin({ "http://localhost:8081", "http://192.168.1.106:8081/" })
@RequestMapping(value = "/api/product", method = RequestMethod.POST)
@Tag(name = "Product", description = "Operation with product")
public class PostController {

    private final ProductService productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    public PostController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets by filter.
     *
     * @param categoriesId the categories id
     * @param page_number  the page number
     * @param page_size    the page size
     * @param filter       the filter
     *
     * @return the by filter
     */
    @RequestMapping(path = "/categories/{categoriesId}",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Page<Product> getByFilter(
            @PathVariable(value = "categoriesId") Long categoriesId,
            @RequestParam(name = "number", required = true,
                          defaultValue = "1") Integer page_number,
            @RequestParam(name = "size", required = true,
                          defaultValue = "20") Integer page_size,
            @RequestBody() List<CharacteristicsRequest> filter
    ) {
        System.out.println("i'am here " + filter);
        return productService.findByCategoriesFilteredCharacteristics(
                PageRequest.of(page_number, page_size),
                categoriesId,
                filter.stream()
                      .map(Characteristics::new)
                      .collect(Collectors.toList())
        );
    }

    /**
     * REST service endpoint
     * Save product
     *
     * @param product Object Product
     */
    @RequestMapping(path = "/save", method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody Product product) {
        productService.save(product);
    }

    /**
     * REST service endpoint
     * Update product
     *
     * @param product Object Product
     */
    @RequestMapping(path = "/update", method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Product product) {
        productService.update(product);
    }

    /**
     * REST service endpoint
     * Delete product
     *
     * @param product Object Product
     */
    @RequestMapping(path = "/delete", method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody Product product) {
        productService.delete(product);
    }

}

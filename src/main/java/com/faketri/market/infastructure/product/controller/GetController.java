package com.faketri.market.infastructure.product.controller;

import com.faketri.market.entity.product.model.Product;
import com.faketri.market.infastructure.product.gateway.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Product controller.
 *
 * @author Dmitriy Faketri
 */
@RestController()
@Log4j2
@CrossOrigin({ "http://localhost:8081", "http://192.168.1.106:8081/" })
@RequestMapping(value = "/api/product", method = RequestMethod.GET)
@Tag(name = "Product", description = "Operation with product")
public class GetController {

    private final ProductService productService;

    public GetController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * REST get all product
     *
     * @return Collection of product
     */
    @ApiResponses()
    @RequestMapping(path = "/", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Product> getAll() {
        return productService.findAll();
    }

    /**
     * REST service endpoint - '/categories/{categoriesId}?number=1&size=20'
     *
     * @param categoriesId id categories,
     * @param page_number  number of page product
     * @param page_size    count item on page
     *
     * @return Page products in categories
     */
    @RequestMapping(path = "/categories/{categoriesId}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Page<Product> getByCategories(
            @PathVariable(value = "categoriesId") Long categoriesId,
            @RequestParam(name = "number", required = true,
                          defaultValue = "1") Integer page_number,
            @RequestParam(name = "size", required = true,
                          defaultValue = "20") Integer page_size
    ) {
        return productService.findByCategories(categoriesId,
                                               PageRequest.of(page_number,
                                                              page_size
                                               )
        );
    }

    /**
     * Gets promotion product.
     *
     * @param page_number the page number
     * @param page_size   the page size
     *
     * @return the promotion product
     */
    @RequestMapping(path = "/promotion/", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Page<Product> getPromotionProduct(
            @RequestParam(name = "number", required = true,
                          defaultValue = "1") Integer page_number,
            @RequestParam(name = "size", required = true,
                          defaultValue = "20") Integer page_size
    ) {
        return productService.findPromotionProduct(PageRequest.of(page_number,
                                                                  page_size
        ));
    }

    /**
     * REST service endpoint - '/product?number=1&size=20'
     *
     * @param page_number number of page product
     * @param page_size   count item on page
     *
     * @return Page Product
     */
    @RequestMapping(path = "/product", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Page<Product> getAll(
            @RequestParam(name = "number", required = true,
                          defaultValue = "1") Integer page_number,
            @RequestParam(name = "size", required = true,
                          defaultValue = "20") Integer page_size
    ) {
        return productService.findAll(PageRequest.of(page_number, page_size));
    }

    /**
     * REST service endpoint
     *
     * @param productId id product
     *
     * @return product with productId
     */
    @RequestMapping(path = "/{productId}", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Product getProduct(
            @PathVariable(value = "productId") Long productId
    ) {
        return productService.findById(productId);
    }

    /**
     * REST service endpoint
     *
     * @param page_number number of page
     * @param page_size   count item on page
     *
     * @return top-selling product
     */
    @RequestMapping(path = "/top-selling", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Page<Product> getTopSelling(
            @RequestParam(name = "number", required = true,
                          defaultValue = "1") Integer page_number,
            @RequestParam(name = "size", required = true,
                          defaultValue = "20") Integer page_size
    ) {
        return productService.findTopSelling(PageRequest.of(page_number,
                                                            page_size
        ));
    }

}

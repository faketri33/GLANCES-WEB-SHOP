package com.faketri.market.contoller;

import com.faketri.market.entity.Product;
import com.faketri.market.entity.enums.EBrand;
import com.faketri.market.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@RequestMapping("/api/product")
@Tag(name = "Product", description = "Operation with product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Get all product's")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {
                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = Product.class)) },
                    description = "Return list product"
            )
    })
    public @ResponseBody List<Product> getAllProduct(){
        return productService.findAll();
    }

    @RequestMapping(path = "/page",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Get page product's")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Product.class)) },
                    description = "Return page with product"
            )
    })
    public @ResponseBody Page<Product> getProductPage(
            @Parameter
            @RequestParam("number") Integer page_number,
            @Parameter
            @RequestParam("size") Integer page_size){
        Pageable page = PageRequest.of(page_number, page_size);
        return productService.findByAllByPage(page);
    }

    @RequestMapping(path = "/{productId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Product getProduct(@PathVariable(value = "productId") Long productId){
        return productService.findById(productId);
    }

    @RequestMapping(path = "/brandName",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody EBrand[] getAllProductBrandName(){
        return EBrand.values();
    }
}

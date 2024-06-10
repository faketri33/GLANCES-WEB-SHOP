package com.faketri.market.infastructure.product.payload.product.controller;

import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.infastructure.product.payload.product.dto.ProductCreateRequest;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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
    private final String root = new ClassPathResource("/images/").getPath();

    public ProductPostController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void save(
            @Valid @RequestPart("product") final ProductCreateRequest productCreateRequest,
            @RequestPart("images") final List<MultipartFile> images) {
        productService.save(productCreateRequest, images);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping("/update")
    public void update(@Valid @RequestPart("product") final Product product,
                       @RequestPart(value = "images", required = false) final List<MultipartFile> images) {
        productService.update(product, images);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id")UUID id) {
        productService.deleteById(id);
    }

}

package com.faketri.market.infastructure.product.payload.categories.controller;


import com.faketri.market.entity.product.payload.categories.model.Categories;
import com.faketri.market.infastructure.product.payload.categories.dto.CategoriesRequest;
import com.faketri.market.infastructure.product.payload.categories.gateway.CategoriesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

/**
 * The type Categories controller.
 *
 * @author Dmitriy Faketri
 */
@RestController
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "categories", description = "Operation with categories")
public class CategoriesController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Categories> getAll() {
        return categoriesService.findAll();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Categories> findByName(@RequestParam("name") String name,@RequestParam(name = "number", required = true,
                                       defaultValue = "0") final Integer pageNumber,
    @RequestParam(name = "size", required = true,
    defaultValue = "20") final Integer pageSize) {
        name = URLDecoder.decode(name, StandardCharsets.UTF_8);
        log.info("findByName: " + URLDecoder.decode(name, StandardCharsets.UTF_8));
        return categoriesService.findByName(name, PageRequest.of(pageNumber, pageSize));
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
    public Categories save(@RequestPart("categories") final CategoriesRequest categoriesRequest,
                           @RequestPart("images") final MultipartFile images) {
        return categoriesService.create(categoriesRequest, images);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
    public Categories update(@RequestPart("categories") final Categories categoriesRequest,
                           @RequestPart(value = "images", required = false) final MultipartFile images) {
        return categoriesService.update(categoriesRequest, images);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Categories categories) {
        categoriesService.delete(categories);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") UUID id) {
        categoriesService.deleteById(id);
    }

}

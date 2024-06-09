package com.faketri.market.infastructure.product.payload.brand.controller;

import com.faketri.market.entity.product.payload.brand.model.Brand;
import com.faketri.market.infastructure.product.payload.brand.dto.BrandRequest;
import com.faketri.market.infastructure.product.payload.brand.gateway.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(value = "/api/brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Brand> findAll() {
        return brandService.findAll();
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<Brand> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                               @RequestParam(name = "size", defaultValue = "20") Integer size) {
        return brandService.findAll(PageRequest.of(page, size));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<Brand> save(@RequestParam String name, @RequestParam(name = "number", required = true,
            defaultValue = "0") final Integer pageNumber,
                      @RequestParam(name = "size", required = true,
                              defaultValue = "20") final Integer pageSize) {
        name = URLDecoder.decode(name, StandardCharsets.UTF_8);
        return brandService.searchByName(name, PageRequest.of(pageNumber, pageSize));
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Brand save(@RequestBody Brand brand) {
        log.info("save: " + brand);
        return brandService.save(brand);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Brand update(@RequestBody Brand brand) {
        log.info("update: " + brand);
        if( brand.getId() == null || brand.getName() == null)
            throw new IllegalArgumentException("Бренд не может быть пустой.");
        return brandService.update(brand);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Brand brand) {
        brandService.delete(brand);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id")UUID id) {
        brandService.deleteById(id);
    }
}

package com.faketri.market.infastructure.product.payload.brand.controller;

import com.faketri.market.entity.product.payload.brand.model.Brand;
import com.faketri.market.infastructure.product.payload.brand.gateway.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(value = "/api/brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandController {

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

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Brand save(@RequestBody Brand brand) {
        return brandService.save(brand);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Brand brand) {
        brandService.delete(brand);
    }
}

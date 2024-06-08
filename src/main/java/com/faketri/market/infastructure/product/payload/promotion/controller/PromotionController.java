package com.faketri.market.infastructure.product.payload.promotion.controller;


import com.faketri.market.entity.product.payload.promotion.model.Promotion;
import com.faketri.market.infastructure.product.payload.promotion.gateway.PromotionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
 * The type Promotion controller.
 *
 * @author Dmitriy Faketri
 */
@RestController()
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/promotion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Promotion", description = "Operation with promotion")
public class PromotionController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @RequestMapping("/")
    public List<Promotion> getAll() {
        return promotionService.findAll();
    }

    @RequestMapping("/{id}")
    public Promotion findById(@PathVariable("id") UUID id) {
        return promotionService.findById(id);
    }

    @RequestMapping("/search")
    public Page<Promotion> likeByName(@RequestParam("name") String name,
                                      @RequestParam(name = "number", required = true, defaultValue = "0")
                                final Integer pageNumber,
                                      @RequestParam(name = "size", required = true, defaultValue = "20")
                                final Integer pageSize) {
        name = URLDecoder.decode(name, StandardCharsets.UTF_8);
        log.info("likeByName: " + name);
        return promotionService.likeByName(name, PageRequest.of(pageNumber, pageSize));
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Promotion save(
            @Valid @RequestPart("promo") final Promotion promotion,
            @RequestPart("images") final MultipartFile images) {
        return promotionService.create(promotion, images);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Promotion update(
            @Valid @RequestPart("promo") final Promotion promotion,
            @RequestPart(value = "images", required = false) final MultipartFile images) {
        return promotionService.update(promotion, images);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void delete(@PathVariable("id") UUID id) {
        promotionService.deleteById(id);
    }

}

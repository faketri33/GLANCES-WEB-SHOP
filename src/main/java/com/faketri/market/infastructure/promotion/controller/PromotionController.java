package com.faketri.market.infastructure.promotion.controller;

import com.faketri.market.entity.promotion.model.Promotion;
import com.faketri.market.infastructure.promotion.gateway.PromotionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Promotion controller.
 *
 * @author Dmitriy Faketri
 */
@RestController()
@CrossOrigin({ "http://localhost:8081", "http://192.168.1.106:8081/" })
@RequestMapping("/api/promotion")
@Tag(name = "Promotion", description = "Operation with promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    /**
     * Gets all.
     *
     * @return the all
     */
    @RequestMapping(path = "/", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Promotion> getAll() {
        return promotionService.findAll();
    }

    /**
     * Find by id promotion.
     *
     * @param id the id
     *
     * @return the promotion
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Promotion findById(@PathVariable("id") Long id) {
        return promotionService.findById(id);
    }

}

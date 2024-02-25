package com.faketri.market.infastructure.product.controller;

import com.faketri.market.entity.product.model.Characteristics;
import com.faketri.market.infastructure.product.gateway.CharacteristicsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Characteristics controller.
 *
 * @author Dmitriy Faketri
 */
@RestController()
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/characteristics")
@Tag(name = "Characteristics", description = "")
public class CharacteristicsController {

    @Autowired
    private CharacteristicsService characteristicsService;

    /**
     * Find characteristics by product category list.
     *
     * @param categoryId the category id
     *
     * @return the list
     */
    @RequestMapping(path = "/product/{categoryId}", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Characteristics> findCharacteristicsByProductCategory(
            @PathVariable(name = "categoryId") Long categoryId
    ) {
        return characteristicsService.findCharacteristicsByProductCategory(
                categoryId);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    @RequestMapping(path = "/", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Characteristics> findAll() {
        return characteristicsService.findAll();
    }

}

package com.faketri.market.infastructure.characteristics.controller;

import com.faketri.market.entity.product.model.child.Characteristics;
import com.faketri.market.infastructure.characteristics.gateway.CharacteristicsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Characteristics controller.
 *
 * @author Dmitriy Faketri
 */
@RestController()
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/characteristics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Characteristics", description = "")
public class CharacteristicsController {

    private final CharacteristicsService characteristicsService;

    public CharacteristicsController(CharacteristicsService characteristicsService) {
        this.characteristicsService = characteristicsService;
    }

    /**
     * Find characteristics by product category list.
     *
     * @param categoryId the category id
     * @return the list
     */
    @RequestMapping("/product/{categoryId}")
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
    @RequestMapping("/")
    public List<Characteristics> findAll() {
        return characteristicsService.findAll();
    }

}

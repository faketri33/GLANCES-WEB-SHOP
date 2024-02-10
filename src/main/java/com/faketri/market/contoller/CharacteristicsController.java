package com.faketri.market.contoller;

import com.faketri.market.domain.product.Characteristics;
import com.faketri.market.service.product.CharacteristicsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/characteristics")
@Tag(name = "Characteristics", description = "")
public class CharacteristicsController {

    @Autowired
    private CharacteristicsService characteristicsService;

    @RequestMapping(path = "/product/{categoryId}", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Characteristics> findCharacteristicsByProductCategory(
            @PathVariable(name = "categoryId") Long categoryId
    ) {
        return characteristicsService.findCharacteristicsByProductCategory(
                categoryId);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Characteristics> findAll() {
        return characteristicsService.findAll();
    }

}

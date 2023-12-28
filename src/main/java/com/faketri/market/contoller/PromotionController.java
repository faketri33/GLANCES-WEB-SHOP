package com.faketri.market.contoller;

import com.faketri.market.domain.Promo.Promotion;
import com.faketri.market.service.PromotionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/promotion")
@Tag(name = "Promotion", description = "Operation with promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @RequestMapping(path = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Promotion> getAll(){
        return promotionService.findAll();
    }

    @RequestMapping(path = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Promotion findById(@PathVariable("id") Long id){
        return promotionService.findById(id);
    }

}

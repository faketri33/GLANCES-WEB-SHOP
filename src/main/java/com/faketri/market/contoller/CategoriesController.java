package com.faketri.market.contoller;

import com.faketri.market.domain.product.Categories;
import com.faketri.market.service.product.CategoriesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/categories")
@Tag(name = "categories", description = "Operation with categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(path = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Categories> getAll(){
        return categoriesService.findAll();
    }
}

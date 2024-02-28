package com.faketri.market.infastructure.product.controller.child;

import com.faketri.market.entity.product.model.child.Categories;
import com.faketri.market.infastructure.product.gateway.child.CategoriesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Categories controller.
 *
 * @author Dmitriy Faketri
 */
@RestController()
@CrossOrigin({ "http://localhost:8081", "http://192.168.1.106:8081/" })
@RequestMapping("/api/categories")
@Tag(name = "categories", description = "Operation with categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    /**
     * Gets all.
     *
     * @return the all
     */
    @RequestMapping(path = "/", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Categories> getAll() {
        return categoriesService.findAll();
    }

}

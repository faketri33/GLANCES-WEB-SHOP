package com.faketri.market.contoller;


import com.faketri.market.entity.Product;
import com.faketri.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productDao;

    @RequestMapping(path = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Product> welcome(){
        return productDao.findAll();
    }
}

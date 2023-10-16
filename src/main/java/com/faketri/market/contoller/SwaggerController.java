package com.faketri.market.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/swaggerDocs")
public class SwaggerController {

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = "text/html")
    public String getSwaggerApiDocs(){
        return "swagger-ui";
    }

}

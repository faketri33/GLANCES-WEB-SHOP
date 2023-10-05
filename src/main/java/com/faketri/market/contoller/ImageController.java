package com.faketri.market.contoller;

import com.faketri.market.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(path = "/image/{idImage}",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageById(@PathVariable(value = "idImage") Long idImage){
        return imageService.findById(idImage).getPhoto();
    }
}

package com.faketri.market.contoller;

import com.faketri.market.entity.Image;
import com.faketri.market.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(path = "/image/{idImage}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageById(@PathVariable(value = "idImage") Long idImage){
        return imageService.findById(idImage).getPhoto();
    }

    @RequestMapping(path = "/image/product/{productId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody List<Image> getImageByProductId(@PathVariable(value = "productId") Long productId){
        return imageService.findByProductId(productId);
    }
}

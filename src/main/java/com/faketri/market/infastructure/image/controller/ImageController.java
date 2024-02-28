package com.faketri.market.infastructure.image.controller;

import com.faketri.market.infastructure.image.gateway.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;


/**
 * The type Image controller.
 *
 * @author Dmitriy Faketri
 */
@RestController
@CrossOrigin({ "http://localhost:8081", "http://192.168.1.106:8081/" })
@RequestMapping("/api/image")
@Tag(name = "Characteristics", description = "")
public class ImageController {

    @Autowired
    private ImageService imageService;

    /**
     * Gets image get id.
     *
     * @param id       the id
     * @param response the response
     *
     * @throws IOException the io exception
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public void getImageGetId(@PathVariable Long id,
                              HttpServletResponse response
    ) throws IOException {
        InputStream in = new ClassPathResource(imageService.findById(id)
                                                           .getPath()).getInputStream();
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

}

package com.faketri.market.infastructure.image.controller;

import com.faketri.market.entity.image.model.Image;
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
import java.util.UUID;


/**
 * The type Image controller.
 *
 * @author Dmitriy Faketri
 */
@RestController
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/image", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Characteristics", description = "")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Gets image get id.
     *
     * @param id       the id
     * @param response the response
     * @throws IOException the io exception
     */
    @RequestMapping("/{id}")
    public void getImageGetId(@PathVariable final UUID id,
                              HttpServletResponse response
    ) throws IOException {
        try {
            final Image image = imageService.findById(id);
            final ClassPathResource classPathResource = new ClassPathResource(image.getPath());
            System.out.println(classPathResource.getPath());
            final InputStream in = classPathResource.getInputStream();
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            IOUtils.copy(in, response.getOutputStream());
        } catch (Exception e) {
            final InputStream in = new ClassPathResource("images/NotFound.png").getInputStream();
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            IOUtils.copy(in, response.getOutputStream());
        }
    }

}

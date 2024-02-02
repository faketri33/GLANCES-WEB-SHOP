package com.faketri.market.service.product;

import com.faketri.market.domain.image.Image;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.impl.ImageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageImpl imageImpl;

    public Image findById(Long id) {
        return imageImpl.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Image with id - " + id + " not found"));
    }

    public List<Image> findAll() {
        return imageImpl.findAll();
    }

    public Page<Image> findAll(Pageable pageable) {
        return imageImpl.findAll(pageable);
    }

    public Image save(Image entity) {
        Image image = imageImpl.findByFields(entity);
        return image == null ? imageImpl.save(entity) : image;
    }

    public Boolean update(Image entity) {
        return imageImpl.update(entity);
    }

    public Boolean delete(Image entity) {
        return imageImpl.delete(entity);
    }

}

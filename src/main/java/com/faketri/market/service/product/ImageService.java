package com.faketri.market.service.product;

import com.faketri.market.domain.image.Image;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageImpl;

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
        return imageImpl.save(entity);
    }

    public Boolean update(Image entity) {
        return null;
    }

    public void delete(Image entity) {
        imageImpl.delete(entity);
    }

}

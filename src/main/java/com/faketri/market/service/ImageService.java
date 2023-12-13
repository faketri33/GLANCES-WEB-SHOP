package com.faketri.market.service;

import com.faketri.market.entity.Image;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.Repository;
import com.faketri.market.repository.impl.ImageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageImpl image;

    public Image findById(Long id) {
        return image.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Image with id - " + id + " not found")
        );
    }

    public List<Image> findAll() {
        return image.findAll();
    }

    public Page<Image> findAll(Pageable pageable) {
        return image.findAll(pageable);
    }

    public Long save(Image entity) {
        return image.save(entity);
    }

    public Boolean update(Image entity) {
        return image.update(entity);
    }

    public Boolean delete(Image entity) {
        return image.delete(entity);
    }

}

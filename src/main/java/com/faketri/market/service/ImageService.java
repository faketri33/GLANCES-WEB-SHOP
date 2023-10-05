package com.faketri.market.service;

import com.faketri.market.entity.Image;
import com.faketri.market.exception.responseEntity.ResourceNotFoundException;
import com.faketri.market.repository.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageDao imageDao;

    public Image findById(Long id){
        return imageDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Image with id " + id + " not found")
        );
    }

    public void save(Image image){
        imageDao.save(image);
    }
}

package com.faketri.market.service;

import com.faketri.market.entity.Image;
import com.faketri.market.repository.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageDao imageDao;

    public Image findById(Long id){
        return imageDao.findById(id).orElseThrow(
                () -> new RuntimeException("Not founded")
        );
    }
    public List<Image> findByProductId(Long productId){
        return findByProductId(productId);
    }

    public void save(Image image){
        imageDao.save(image);
    }
}

package com.faketri.market.service;

import com.faketri.market.entity.Image;
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
                () -> new RuntimeException(String.format("Not founded image by id - %d", id))
        );
    }
    public List<Image> findByProductId(Long productId){
        return imageDao.findByProductId(productId);
    }

    public void save(Image image){
        imageDao.save(image);
    }
}

package com.faketri.market.usecase.image;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.image.gateway.ImageRepository;
import com.faketri.market.entity.image.model.Image;
import com.faketri.market.infastructure.image.gateway.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageImpl;

    @Autowired
    public ImageServiceImpl(ImageRepository imageImpl) {
        this.imageImpl = imageImpl;
    }

    public Image findById(UUID id) {
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
        return imageImpl.findFirstByPath(entity.getPath())
                .orElse(imageImpl.save(entity));
    }

    @Override
    public Boolean update(Image entity) {
        return null;
    }

    public void delete(Image entity) {
        System.out.println(entity.getId());
        File file = new File(entity.getPath());

        if (file.exists()) file.delete();

        imageImpl.deleteById(entity.getId());
    }

    public void delete(UUID entity) {
        System.out.println(entity);
        imageImpl.deleteById(entity);
    }

}

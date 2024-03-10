package com.faketri.market.usecase.image;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.image.gateway.ImageRepository;
import com.faketri.market.entity.image.model.Image;
import com.faketri.market.infastructure.image.gateway.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The type Image service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageImpl;

    @Autowired
    public ImageServiceImpl(ImageRepository imageImpl) {
        this.imageImpl = imageImpl;
    }

    /**
     * Find by id image.
     *
     * @param id the id
     * @return the image
     */
    public Image findById(UUID id) {
        return imageImpl.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Image with id - " + id + " not found"));
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Image> findAll() {
        return imageImpl.findAll();
    }

    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     */
    public Page<Image> findAll(Pageable pageable) {
        return imageImpl.findAll(pageable);
    }

    /**
     * Save image.
     *
     * @param entity the entity
     * @return the image
     */
    public Image save(Image entity) {
        return imageImpl.findFirstByPath(entity.getPath())
                .orElse(imageImpl.save(entity));
    }

    @Override
    public Boolean update(Image entity) {
        return null;
    }

    /**
     * Delete.
     *
     * @param entity the entity
     */
    public void delete(Image entity) {
        imageImpl.delete(entity);
    }

}

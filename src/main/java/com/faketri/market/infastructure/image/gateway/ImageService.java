package com.faketri.market.infastructure.image.gateway;

import com.faketri.market.entity.image.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ImageService {

    Image findById(Long id);

    List<Image> findAll();

    Page<Image> findAll(Pageable pageable);

    Image save(Image entity);

    Boolean update(Image entity);

    void delete(Image entity);

}

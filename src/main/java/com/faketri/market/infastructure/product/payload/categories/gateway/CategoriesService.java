package com.faketri.market.infastructure.product.payload.categories.gateway;

import com.faketri.market.entity.product.payload.categories.model.Categories;
import com.faketri.market.infastructure.product.payload.categories.dto.CategoriesRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface CategoriesService {

    Categories findById(UUID id);

    List<Categories> findAll();

    Page<Categories> findAll(Pageable pageable);

    Categories create(CategoriesRequest categories, MultipartFile images);

    Categories save(Categories categories);

    void delete(Categories categories);

}

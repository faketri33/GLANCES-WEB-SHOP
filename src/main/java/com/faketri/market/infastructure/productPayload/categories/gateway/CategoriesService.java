package com.faketri.market.infastructure.productPayload.categories.gateway;

import com.faketri.market.entity.productPayload.categories.model.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CategoriesService {

    Categories findById(UUID id);

    List<Categories> findAll();

    Page<Categories> findAll(Pageable pageable);

    Categories save(Categories categories);

    void delete(Categories categories);

}

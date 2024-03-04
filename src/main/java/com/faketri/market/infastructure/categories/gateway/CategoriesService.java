package com.faketri.market.infastructure.categories.gateway;

import com.faketri.market.entity.product.model.child.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoriesService {

    Categories findById(Long id);

    List<Categories> findAll();

    Page<Categories> findAll(Pageable pageable);

    Categories save(Categories categories);

    void delete(Categories categories);

}

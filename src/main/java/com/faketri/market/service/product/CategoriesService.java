package com.faketri.market.service.product;

import com.faketri.market.domain.product.Categories;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.impl.CategoriesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesImpl categoriesImpl;

    public Categories findById(Long id) {
        return categoriesImpl.findById(id)
                             .orElseThrow(() -> new ResourceNotFoundException(
                                     "Categories with id - " + id + " not found"));
    }

    public List<Categories> findAll() {return categoriesImpl.findAll();}

    public Page<Categories> findAll(Pageable pageable
    ) {return categoriesImpl.findAll(pageable);}

    public Categories save(Categories categories) {
        Categories entity = categoriesImpl.findByFields(categories);
        return entity == null ? categoriesImpl.save(categories) : entity;
    }

    public Boolean update(Categories categories) {
        return categoriesImpl.update(categories);
    }

    public Boolean delete(Categories categories) {
        return categoriesImpl.delete(categories);
    }

}

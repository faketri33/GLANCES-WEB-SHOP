package com.faketri.market.service.product;

import com.faketri.market.domain.product.Categories;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesImpl;

    public Categories findById(Long id) {
        return categoriesImpl.findById(id)
                             .orElseThrow(() -> new ResourceNotFoundException(
                                     "Categories with id - " + id + " not found"));
    }

    public List<Categories> findAll() {return categoriesImpl.findAll();}

    public Page<Categories> findAll(Pageable pageable
    ) {return categoriesImpl.findAll(pageable);}

    public Categories save(Categories categories) {
        return categoriesImpl.save(categories);
    }

    /*public Boolean update(Categories categories) {
        return categoriesImpl.update(categories);
    }*/

    public void delete(Categories categories) {
        categoriesImpl.delete(categories);
    }

}

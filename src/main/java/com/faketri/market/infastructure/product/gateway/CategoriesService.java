package com.faketri.market.infastructure.product.gateway;

import com.faketri.market.entity.product.model.Categories;
import com.faketri.market.infastructure.config.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.gateway.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Categories service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesImpl;

    /**
     * Find by id categories.
     *
     * @param id the id
     *
     * @return the categories
     */
    public Categories findById(Long id) {
        return categoriesImpl.findById(id)
                             .orElseThrow(() -> new ResourceNotFoundException(
                                     "Categories with id - " + id + " not found"));
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Categories> findAll() {return categoriesImpl.findAll();}

    /**
     * Find all page.
     *
     * @param pageable the pageable
     *
     * @return the page
     */
    public Page<Categories> findAll(Pageable pageable
    ) {return categoriesImpl.findAll(pageable);}

    /**
     * Save categories.
     *
     * @param categories the categories
     *
     * @return the categories
     */
    public Categories save(Categories categories) {
        return categoriesImpl.findByName(categories.getName())
                             .orElse(categoriesImpl.save(categories));
    }

    /*public Boolean update(Categories categories) {
        return categoriesImpl.update(categories);
    }*/

    /**
     * Delete.
     *
     * @param categories the categories
     */
    public void delete(Categories categories) {
        categoriesImpl.delete(categories);
    }

}

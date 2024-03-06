package com.faketri.market.usecase.categories;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.gateway.repo.child.CategoriesRepository;
import com.faketri.market.entity.product.model.child.Categories;
import com.faketri.market.infastructure.categories.gateway.CategoriesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The type Categories service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesImpl;

    public CategoriesServiceImpl(CategoriesRepository categoriesImpl) {
        this.categoriesImpl = categoriesImpl;
    }

    /**
     * Find by id categories.
     *
     * @param id the id
     * @return the categories
     */
    public Categories findById(UUID id) {
        return categoriesImpl.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categories with id - " + id + " not found"));
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Categories> findAll() {
        return categoriesImpl.findAll();
    }

    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     */
    public Page<Categories> findAll(Pageable pageable
    ) {
        return categoriesImpl.findAll(pageable);
    }

    /**
     * Save categories.
     *
     * @param categories the categories
     * @return the categories
     */
    public Categories save(Categories categories) {
        return categoriesImpl.findByName(categories.getName())
                .orElse(categoriesImpl.save(categories));
    }

    /**
     * Delete.
     *
     * @param categories the categories
     */
    public void delete(Categories categories) {
        categoriesImpl.delete(categories);
    }

}

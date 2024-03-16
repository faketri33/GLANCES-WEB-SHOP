package com.faketri.market.usecase.productPayload.categories;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.productPayload.categories.gateway.CategoriesRepository;
import com.faketri.market.entity.productPayload.categories.model.Categories;
import com.faketri.market.infastructure.productPayload.categories.gateway.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    /**
     * Find by id categories.
     *
     * @param id the id
     * @return the categories
     */
    public Categories findById(UUID id) {
        return categoriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categories with id - " + id + " not found"));
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     */
    public Page<Categories> findAll(Pageable pageable
    ) {
        return categoriesRepository.findAll(pageable);
    }

    /**
     * Save categories.
     *
     * @param categories the categories
     * @return the categories
     */
    public Categories save(Categories categories) {
        return categoriesRepository.findByName(categories.getName())
                .orElseGet(() -> categoriesRepository.save(categories));
    }

    /**
     * Delete.
     *
     * @param categories the categories
     */
    public void delete(Categories categories) {
        categoriesRepository.delete(categories);
    }

}

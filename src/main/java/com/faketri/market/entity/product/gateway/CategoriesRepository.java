package com.faketri.market.entity.product.gateway;

import com.faketri.market.entity.product.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Categories repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    @Override
    Optional<Categories> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    List<Categories> findAll();

    /**
     * Exists by name boolean.
     *
     * @param name the name
     *
     * @return the boolean
     */
    boolean existsByName(String name);

    /**
     * Find by name optional.
     *
     * @param name the name
     *
     * @return the optional
     */
    Optional<Categories> findByName(String name);

}
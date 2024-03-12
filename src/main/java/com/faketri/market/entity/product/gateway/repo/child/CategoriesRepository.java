package com.faketri.market.entity.product.gateway.repo.child;

import com.faketri.market.entity.product.model.child.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface Categories repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface CategoriesRepository extends JpaRepository<Categories, UUID> {

    Optional<Categories> findById(UUID aLong);

    void deleteById(UUID aLong);

    @Override
    List<Categories> findAll();

    /**
     * Exists by name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    boolean existsByName(String name);

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Categories> findByName(String name);

}
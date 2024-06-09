package com.faketri.market.entity.product.payload.categories.gateway;

import com.faketri.market.entity.product.payload.categories.model.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    boolean existsByName(String name);

    Optional<Categories> findByName(String name);

    Categories save(Categories categories);

    @Query("select c from Categories c where LOWER(c.name) like ?1")
    Page<Categories> findByNameLike(String name, Pageable pageable);
}
package com.faketri.market.repository;

import com.faketri.market.domain.product.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    @Override
    Optional<Categories> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    List<Categories> findAll();

    boolean existsByName(String name);

    Optional<Categories> findByName(String name);

}
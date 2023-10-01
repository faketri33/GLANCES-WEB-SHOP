package com.faketri.market.repository;

import com.faketri.market.entity.Product;
import com.faketri.market.entity.enums.ECategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);
    List<Product> findByCategories(ECategories eCategories);

    //TODO : MAKE A SEARCH by start price
    //List<Product> findByPriceFromStartBeforeStop(double start, double stop);
}

package com.faketri.market.repository;

import com.faketri.market.entity.Product;
import com.faketri.market.entity.enums.ECategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    List<Product> findByCategories(ECategories eCategories);

    // TODO : search by price range
    //List<Product> findByPriceFromStartBeforeStop(double start, double stop);
}

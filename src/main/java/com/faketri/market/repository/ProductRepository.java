package com.faketri.market.repository;

import com.faketri.market.entity.Brand;
import com.faketri.market.entity.Categories;
import com.faketri.market.entity.Characteristics;
import com.faketri.market.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Repository<Long, Product> {
    List<Product> findByBrand(Brand brand);
    List<Product> findByCategories(Long categoriesId);
    List<Product> findByCharacteristics(Characteristics characteristics);
    Page<Product> findByBrand(Brand brand, Pageable pageable);
    Page<Product> findByCharacteristics(Characteristics characteristics, Pageable pageable);
    Page<Product> findByCharacteristics(List<Characteristics> characteristics, Pageable pageable);
    Page<Product> findByCategories(Long categoriesId, Pageable pageable);
    Page<Product> findTopSelling(Pageable pageable);
}

package com.faketri.market.repository;

import com.faketri.market.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao extends CrudRepository<Product, Long> {

    //@Query("select * from product p where p.id = :id")
    Optional<Product> findById(@Param("id") Long id);
    List<Product> findAll();
    //@Query("select * from product p left join product_categories pc on pc.product_id = p.id left join categories c on c.id = :categories")
    //List<Product> findByCategories(Long categoriesId);
    //@Query("select * from product p left join product_characteristics pc on pc.product_id = p.id left join characteristics c on c.id = :characteristics")
    //List<Product> findByCharacteristics(Long characteristicsId);
    Page<Product> findAll(Pageable pageable);
    //Page<Product> findByCategories(Categories categories, Pageable pageable);
    //Page<Product> findByCharacteristics(Characteristics characteristics, Pageable pageable);
}

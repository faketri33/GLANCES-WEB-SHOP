package com.faketri.market.entity.product.gateway.repo;

import com.faketri.market.entity.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The interface Product repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Optional<Product> findOne(Specification<Product> spec);

    List<Product> findAll(Specification<Product> spec);

    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    List<Product> findAll(Specification<Product> spec, Sort sort);

    long count(Specification<Product> spec);

    boolean exists(Specification<Product> spec);

    long delete(Specification<Product> spec);

    void delete(Product product);

    @Transactional
    @Modifying
    @Query("update Product p set p.nameModel = ?1, p.price = ?2, p.quantity = ?3, p.quantitySold = ?4 where p.id = ?5")
    int update(String nameModel, Long price, int quantity, int quantitySold,
               Long id
    );

}
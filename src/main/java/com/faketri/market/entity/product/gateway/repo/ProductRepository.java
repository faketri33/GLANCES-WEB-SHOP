package com.faketri.market.entity.product.gateway.repo;

import com.faketri.market.entity.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface Product repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Optional<Product> findOne(Specification<Product> spec);

    Optional<Product> findById(UUID id);

    List<Product> findAll(Specification<Product> spec);

    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    List<Product> findAll(Specification<Product> spec, Sort sort);

    long count(Specification<Product> spec);

    boolean exists(Specification<Product> spec);

    long delete(Specification<Product> spec);

    void delete(Product product);
}
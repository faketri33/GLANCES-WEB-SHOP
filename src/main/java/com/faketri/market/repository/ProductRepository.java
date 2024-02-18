package com.faketri.market.repository;

import com.faketri.market.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAll(Specification<Product> specification,
                          Pageable pageable
    );

    @Query("select count(p) from Product p")
    long countFirstBy();

    @Query("select count(p) from Product p where p.categories.id = ?1")
    long countByCategories_Id(Long id);

    @Query("select count(p) from Product p inner join p.characteristics characteristics where characteristics.id = ?1")
    long countByCharacteristics_Id(Long id);

    @Query("select count(p) from Product p where p.brand.id = ?1")
    long countByBrand_Id(Long id);

    @Transactional
    @Modifying
    @Query("update Product p set p.nameModel = ?1, p.price = ?2, p.quantitySold = ?3, p.quantity = ?4 where p.id = ?5")
    int update(String nameModel, Long price, int quantitySold, int quantity,
               Long id
    );

}
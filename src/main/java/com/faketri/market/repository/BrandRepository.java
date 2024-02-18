package com.faketri.market.repository;

import com.faketri.market.domain.product.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Transactional
    @Modifying
    @Query("update Brand b set b.name = ?1 where b.id = ?2")
    int updateNameById(String name, Long id);

    @Override
    Optional<Brand> findById(Long aLong);

    @Override
    List<Brand> findAll();

    @Override
    void deleteById(Long aLong);

    Optional<Brand> findByName(String name);

}
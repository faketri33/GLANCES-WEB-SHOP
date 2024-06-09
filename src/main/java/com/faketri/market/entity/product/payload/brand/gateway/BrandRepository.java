package com.faketri.market.entity.product.payload.brand.gateway;

import com.faketri.market.entity.product.payload.brand.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface Brand repository.
 *
 * @author Dmitriy Faketri
 */
@SuppressWarnings("NullableProblems")
@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {

    @Transactional
    @Modifying
    @Query("update Brand b set b.name = ?1 where b.id = ?2")
    int updateNameById(String name, UUID id);

    Optional<Brand> findById(UUID aLong);

    @Override
    List<Brand> findAll();

    void deleteById(UUID aLong);

    Optional<Brand> findByName(String name);

    @Query("select b from Brand b where LOWER(b.name) like ?1")
    Page<Brand> findByNameLike(String name, Pageable pageable);


}
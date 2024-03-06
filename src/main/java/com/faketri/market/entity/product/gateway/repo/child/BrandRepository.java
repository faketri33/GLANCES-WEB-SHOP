package com.faketri.market.entity.product.gateway.repo.child;

import com.faketri.market.entity.product.model.child.Brand;
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
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    /**
     * Update name by id int.
     *
     * @param name the name
     * @param id   the id
     * @return the int
     */
    @Transactional
    @Modifying
    @Query("update Brand b set b.name = ?1 where b.id = ?2")
    int updateNameById(String name, Long id);

    Optional<Brand> findById(UUID aLong);

    @Override
    List<Brand> findAll();

    void deleteById(UUID aLong);

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Brand> findByName(String name);

}
package com.faketri.market.entity.product.gateway;

import com.faketri.market.entity.product.model.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Rating repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {


    /**
     * Find by product id page.
     *
     * @param id       the id
     * @param pageable the pageable
     *
     * @return the page
     */
    Page<Rating> findByProduct_Id(Long id, Pageable pageable);

    /**
     * Count by product id long.
     *
     * @param id the id
     *
     * @return the long
     */
    long countByProduct_Id(Long id);

}
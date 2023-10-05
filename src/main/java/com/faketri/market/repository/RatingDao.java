package com.faketri.market.repository;

import com.faketri.market.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingDao extends JpaRepository<Rating, Long> {
    Optional<Rating> findById(Long id);

    @Query(value = "SELECT AVG(r.grade) FROM rating r " +
            "inner join product_rating pr on pr.product_id = :productId",
            nativeQuery = true)
    Double averageOfRatingByProductId(@Param("productId") Long productId);
}

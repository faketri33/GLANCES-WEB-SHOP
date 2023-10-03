package com.faketri.market.repository;

import com.faketri.market.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingDao extends JpaRepository<Rating, Long> {
    List<Rating> findByProductId(Long productId);
    @Query("SELECT AVG(e.rating) FROM Rating where product_id = :productId")
    double averageOfRatingByProductId(@Param("productId") long productId);
}

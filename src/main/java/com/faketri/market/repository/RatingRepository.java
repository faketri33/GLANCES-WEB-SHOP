package com.faketri.market.repository;

import com.faketri.market.domain.order.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {


    Page<Rating> findByProduct_Id(Long id, Pageable pageable);

    long countByProduct_Id(Long id);

}
package com.faketri.market.repository;

import com.faketri.market.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageDao extends JpaRepository<Image, Long> {

    Optional<Image> findById(Long id);
    List<Image> findByProductId(Long productId);
}

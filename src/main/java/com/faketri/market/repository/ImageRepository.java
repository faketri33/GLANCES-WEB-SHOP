package com.faketri.market.repository;

import com.faketri.market.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {


    @Transactional
    @Modifying
    @Query("update Image i set i.path = ?1 where i.id = ?2")
    int updateImageById(String path, Long id);

    @Override
    Optional<Image> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    Optional<Image> findFirstByPath(String path);

}
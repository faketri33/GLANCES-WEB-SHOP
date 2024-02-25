package com.faketri.market.entity.image.gateway;

import com.faketri.market.entity.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The interface Image repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Override
    Optional<Image> findById(Long aLong);

    Optional<Image> findFirstByPath(String path);

    @Transactional
    @Modifying
    @Query("update Image i set i.path = ?1 where i.id = ?2")
    int updateImageById(String path, Long id);

    @Override
    void deleteById(Long aLong);


}
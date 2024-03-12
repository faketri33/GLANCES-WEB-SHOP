package com.faketri.market.entity.image.gateway;

import com.faketri.market.entity.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * The interface Image repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {

    Optional<Image> findById(UUID uuid);

    Optional<Image> findFirstByPath(String path);

    void deleteById(UUID uuid);


}
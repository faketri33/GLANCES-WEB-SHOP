package com.faketri.market.entity.product.payload.promotion.gateway;

import com.faketri.market.entity.product.payload.promotion.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface Promotion repository.
 *
 * @author Dmitriy Faketri
 */
public interface PromotionRepository extends JpaRepository<Promotion, UUID> {

    Optional<Promotion> findById(UUID aLong);

    @Override
    List<Promotion> findAll();

    @Override
    void deleteById(UUID aLong);

    @Query("SELECT count(p) > 0 FROM Promotion p JOIN p.promotionProductItems product " +
            "WHERE product.id = ?1 AND p.id != ?3 AND ?2 BETWEEN p.dateOfStart AND p.dateOfEnd")
    boolean findActivePromotionsByProductId(UUID productId, LocalDate currentDate, UUID id);

    @Query("select p from Promotion p where LOWER(p.title) like ?1")
    Page<Promotion> findByTitleLike(String title, Pageable pageable);

}
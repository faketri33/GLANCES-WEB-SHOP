package com.faketri.market.entity.product.payload.characteristics.gateway;

import com.faketri.market.entity.product.payload.characteristics.model.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface Characteristics repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface CharacteristicsRepository
        extends JpaRepository<Characteristics, UUID> {

    @Override
    Optional<Characteristics> findById(UUID aLong);


    @Override
    List<Characteristics> findAll();

    @Transactional
    @Modifying
    @Query("update Characteristics c set c.name = ?1, c.value = ?2 where c.id = ?3")
    int updateNameAndValueById(String name, String value, UUID id);

    @Query("select distinct c from Characteristics c inner join c.products products where products.categories.id = ?1")
    List<Characteristics> findDistinctByProducts_Categories_Id(UUID id);

    Optional<Characteristics> findFirstByNameAndValueOrderByIdAsc(String name, String value);


}
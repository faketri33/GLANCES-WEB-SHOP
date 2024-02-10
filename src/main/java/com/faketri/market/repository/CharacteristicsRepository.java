package com.faketri.market.repository;

import com.faketri.market.domain.product.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacteristicsRepository
        extends JpaRepository<Characteristics, Long> {

    @Override
    Optional<Characteristics> findById(Long aLong);


    @Override
    List<Characteristics> findAll();


    @Transactional
    @Modifying
    @Query("update Characteristics c set c.name = ?1, c.value = ?2 where c.id = ?3")
    int updateNameAndValueById(String name, String value, Long id);

    List<Characteristics> findDistinctByProducts_Categories_Id(Long id);

    Optional<Characteristics> findByNameAndValue(String name, String value);

}
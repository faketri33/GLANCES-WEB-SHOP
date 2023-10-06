package com.faketri.market.repository;

import com.faketri.market.entity.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicsDao extends JpaRepository<Characteristics, Long> {
}

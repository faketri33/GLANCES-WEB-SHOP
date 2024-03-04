package com.faketri.market.infastructure.characteristics.gateway;

import com.faketri.market.entity.product.model.child.Characteristics;

import java.util.List;

public interface CharacteristicsService {

    List<Characteristics> findAll();

    List<Characteristics> findCharacteristicsByProductCategory(Long categoryId);

    Characteristics save(Characteristics characteristics);

}

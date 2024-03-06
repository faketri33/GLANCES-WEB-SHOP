package com.faketri.market.infastructure.characteristics.gateway;

import com.faketri.market.entity.product.model.child.Characteristics;

import java.util.List;
import java.util.UUID;

public interface CharacteristicsService {

    List<Characteristics> findAll();

    List<Characteristics> findCharacteristicsByProductCategory(UUID categoryId);

    Characteristics save(Characteristics characteristics);

}

package com.faketri.market.service.product;

import com.faketri.market.domain.product.Characteristics;
import com.faketri.market.repository.CharacteristicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacteristicsService {

    @Autowired
    private CharacteristicsRepository characteristicsImpl;

    public List<Characteristics> findAll() {
        return characteristicsImpl.findAll();
    }

    public List<Characteristics> findCharacteristicsByProductCategory(
            Long categoryId
    ) {
        return characteristicsImpl.findDistinctByProducts_Categories_Id(
                categoryId);
    }

    public Characteristics save(Characteristics characteristics) {
        return characteristicsImpl.findByNameAndValue(characteristics.getName(),
                                                      characteristics.getValue()
                                  )
                                  .orElse(characteristicsImpl.save(
                                          characteristics));
    }

}

package com.faketri.market.service.product;

import com.faketri.market.domain.product.Characteristics;
import com.faketri.market.repository.impl.CharacteristicsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacteristicsService {

    @Autowired
    private CharacteristicsImpl characteristicsImpl;

    public Characteristics save(Characteristics characteristics) {
        Characteristics entity =
                characteristicsImpl.findByFields(characteristics);
        return entity == null
                ? characteristicsImpl.save(characteristics)
                : entity;
    }

}

package com.faketri.market.infastructure.product.gateway.child;

import com.faketri.market.entity.product.gateway.repo.child.CharacteristicsRepository;
import com.faketri.market.entity.product.model.child.Characteristics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Characteristics service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class CharacteristicsService {

    @Autowired
    private CharacteristicsRepository characteristicsImpl;

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Characteristics> findAll() {
        return characteristicsImpl.findAll();
    }

    /**
     * Find characteristics by product category list.
     *
     * @param categoryId the category id
     *
     * @return the list
     */
    public List<Characteristics> findCharacteristicsByProductCategory(
            Long categoryId
    ) {
        return characteristicsImpl.findDistinctByProducts_Categories_Id(
                categoryId);
    }

    /**
     * Save characteristics.
     *
     * @param characteristics the characteristics
     *
     * @return the characteristics
     */
    public Characteristics save(Characteristics characteristics) {
        return characteristicsImpl.findByNameAndValue(characteristics.getName(),
                                                      characteristics.getValue()
                                  )
                                  .orElse(characteristicsImpl.save(
                                          characteristics));
    }

}

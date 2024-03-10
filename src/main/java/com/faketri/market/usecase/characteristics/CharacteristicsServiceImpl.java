package com.faketri.market.usecase.characteristics;

import com.faketri.market.entity.product.gateway.repo.child.CharacteristicsRepository;
import com.faketri.market.entity.product.model.child.Characteristics;
import com.faketri.market.infastructure.characteristics.gateway.CharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The type Characteristics service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class CharacteristicsServiceImpl implements CharacteristicsService {

    private final CharacteristicsRepository characteristicsImpl;

    @Autowired
    public CharacteristicsServiceImpl(CharacteristicsRepository characteristicsImpl) {
        this.characteristicsImpl = characteristicsImpl;
    }

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
     * @return the list
     */
    public List<Characteristics> findCharacteristicsByProductCategory(
            UUID categoryId
    ) {
        return characteristicsImpl.findDistinctByProducts_Categories_Id(
                categoryId);
    }

    /**
     * Save characteristics.
     *
     * @param characteristics the characteristics
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

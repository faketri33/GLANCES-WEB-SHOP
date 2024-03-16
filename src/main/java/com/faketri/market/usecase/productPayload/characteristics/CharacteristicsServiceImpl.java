package com.faketri.market.usecase.productPayload.characteristics;

import com.faketri.market.entity.productPayload.characteristics.gateway.CharacteristicsRepository;
import com.faketri.market.entity.productPayload.characteristics.model.Characteristics;
import com.faketri.market.infastructure.productPayload.characteristics.gateway.CharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The type Characteristics service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class CharacteristicsServiceImpl implements CharacteristicsService {

    private final CharacteristicsRepository characteristicsRepository;

    @Autowired
    public CharacteristicsServiceImpl(CharacteristicsRepository characteristicsRepository) {
        this.characteristicsRepository = characteristicsRepository;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Characteristics> findAll() {
        return characteristicsRepository.findAll();
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
        return characteristicsRepository.findDistinctByProducts_Categories_Id(
                categoryId);
    }

    @Override
    public Optional<Characteristics> findByNameAndValue(String name, String value) {
        return characteristicsRepository.findByNameAndValue(name, value);
    }

    /**
     * Save characteristics.
     *
     * @param characteristics the characteristics
     * @return the characteristics
     */
    public Characteristics save(Characteristics characteristics) {
        return characteristicsRepository.findByNameAndValue(
                characteristics.getName(),
                characteristics.getValue()
        ).orElseGet(() -> characteristicsRepository.save(characteristics));
    }

}

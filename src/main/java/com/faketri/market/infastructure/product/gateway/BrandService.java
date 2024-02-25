package com.faketri.market.infastructure.product.gateway;

import com.faketri.market.entity.product.model.Brand;
import com.faketri.market.infastructure.config.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.gateway.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Brand service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class BrandService {

    @Autowired
    private BrandRepository brandImpl;

    /**
     * Find by id brand.
     *
     * @param id the id
     *
     * @return the brand
     */
    public Brand findById(Long id) {
        return brandImpl.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Brand with id - " + id + " not found"));
    }

    /**
     * Save brand.
     *
     * @param brand the brand
     *
     * @return the brand
     */
    public Brand save(Brand brand) {
        return brandImpl.findByName(brand.getName())
                        .orElse(brandImpl.save(brand));
    }

}

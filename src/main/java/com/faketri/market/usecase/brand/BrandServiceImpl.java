package com.faketri.market.usecase.brand;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.gateway.repo.child.BrandRepository;
import com.faketri.market.entity.product.model.child.Brand;
import com.faketri.market.infastructure.brand.gateway.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Brand service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class BrandServiceImpl implements BrandService {

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

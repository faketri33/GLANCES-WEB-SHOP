package com.faketri.market.usecase.product.payload.brand;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.payload.brand.gateway.BrandRepository;
import com.faketri.market.entity.product.payload.brand.model.Brand;
import com.faketri.market.infastructure.product.payload.brand.gateway.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * The type Brand service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandImpl;

    @Autowired
    public BrandServiceImpl(BrandRepository brandImpl) {
        this.brandImpl = brandImpl;
    }

    /**
     * Find by id brand.
     *
     * @param id the id
     * @return the brand
     */
    public Brand findById(UUID id) {
        return brandImpl.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Brand with id - " + id + " not found"));
    }

    /**
     * Save brand.
     *
     * @param brand the brand
     * @return the brand
     */
    public Brand save(Brand brand) {
        return brandImpl.findByName(brand.getName())
                .orElse(brandImpl.save(brand));
    }

}

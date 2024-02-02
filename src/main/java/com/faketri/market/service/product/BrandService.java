package com.faketri.market.service.product;

import com.faketri.market.domain.product.Brand;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.impl.BrandImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    @Autowired
    private BrandImpl brandImpl;

    public Brand findById(Long id) {
        return brandImpl.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Brand with id - " + id + " not found"));
    }

    public Brand save(Brand brand) {
        Brand entity = brandImpl.findByFields(brand);
        return entity == null ? brandImpl.save(brand) : entity;
    }

}

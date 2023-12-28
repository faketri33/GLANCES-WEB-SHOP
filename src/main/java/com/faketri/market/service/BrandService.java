package com.faketri.market.service;

import com.faketri.market.domain.product.Brand;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.impl.BrandImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    @Autowired
    private BrandImpl brandImpl;

    public Brand findById(Long id){
        return brandImpl.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Brand with id - " + id + " not found" )
        );
    }

    public Long save(Brand brand){
        return brandImpl.save(brand);
    }
}

package com.faketri.market.infastructure.product.payload.brand.gateway;

import com.faketri.market.entity.product.payload.brand.model.Brand;

import java.util.UUID;

public interface BrandService {

    Brand findById(UUID id);

    Brand save(Brand brand);

}

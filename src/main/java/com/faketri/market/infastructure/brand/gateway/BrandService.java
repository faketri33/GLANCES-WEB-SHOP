package com.faketri.market.infastructure.brand.gateway;

import com.faketri.market.entity.product.model.child.Brand;

import java.util.UUID;

public interface BrandService {

    Brand findById(UUID id);

    Brand save(Brand brand);

}

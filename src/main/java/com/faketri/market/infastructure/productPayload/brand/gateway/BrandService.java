package com.faketri.market.infastructure.productPayload.brand.gateway;

import com.faketri.market.entity.productPayload.brand.model.Brand;

import java.util.UUID;

public interface BrandService {

    Brand findById(UUID id);

    Brand save(Brand brand);

}

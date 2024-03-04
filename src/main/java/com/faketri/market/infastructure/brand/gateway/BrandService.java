package com.faketri.market.infastructure.brand.gateway;

import com.faketri.market.entity.product.model.child.Brand;

public interface BrandService {

    Brand findById(Long id);

    Brand save(Brand brand);

}

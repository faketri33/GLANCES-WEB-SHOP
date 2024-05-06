package com.faketri.market.infastructure.product.payload.brand.gateway;

import com.faketri.market.entity.product.payload.brand.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BrandService {

    Brand findById(UUID id);

    List<Brand> findAll();

    Page<Brand> findAll(Pageable pageable);

    Brand save(Brand brand);

    void deleteById(UUID uuid);

    void delete(Brand brand);

}

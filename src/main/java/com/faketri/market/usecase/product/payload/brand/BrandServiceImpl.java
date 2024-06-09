package com.faketri.market.usecase.product.payload.brand;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.payload.brand.gateway.BrandRepository;
import com.faketri.market.entity.product.payload.brand.model.Brand;
import com.faketri.market.infastructure.product.payload.brand.gateway.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Brand findById(UUID id) {
        return brandImpl.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Brand with id - " + id + " not found"));
    }

    @Override
    public List<Brand> findAll() {
        return brandImpl.findAll();
    }

    @Override
    public Page<Brand> findAll(Pageable pageable) {
        return brandImpl.findAll(pageable);
    }

    @Override
    public Page<Brand> searchByName(String name, Pageable pageable) {
        return brandImpl.findByNameLike('%' + name.toLowerCase().trim() + '%', pageable);
    }

    public Brand save(Brand brand) {
        return brandImpl.findByName(brand.getName())
                .orElse(brandImpl.save(brand));
    }

    public Brand update(Brand brand) {
        return brandImpl.save(brand);
    }

    @Override
    public void deleteById(UUID uuid) {
        brandImpl.deleteById(uuid);
    }

    @Override
    public void delete(Brand brand) {
        brandImpl.delete(brand);
    }

}

package com.faketri.market.service;

import com.faketri.market.entity.*;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.impl.CharacteristicsImpl;
import com.faketri.market.repository.impl.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductImpl productImpl;
    @Autowired
    private ImageService imageService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CharacteristicsImpl characteristicsService;

    public List<Product> findAll() {
        return productImpl.findAll();
    }
    public Page<Product> findAll(Pageable pageable) {
        return productImpl.findAll(pageable);
    }

    public Product findById(Long id){
        return productImpl.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id " + id + " not found")
        );
    }
    public List<Product> findByCategories(Categories categories){
        return productImpl.findByCategories(categories);
    }
    public List<Product> findByCharacteristics(Characteristics characteristics, Pageable pageable){
        return productImpl.findByCharacteristics(characteristics);
    }
    public Long save(Product product){
        Brand brand = product.getBrand();
        if(brand.getId() == null) brand.setId(brandService.save(brand));
        product.getCharacteristics().stream().filter(x -> x.getId() == null).forEach(x -> x.setId(characteristicsService.save(x)));
        product.getImage().stream().filter(x -> x.getId() == null).forEach(x -> x.setId(imageService.save(x)));
        return productImpl.save(product);
    }
}

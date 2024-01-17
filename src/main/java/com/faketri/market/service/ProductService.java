package com.faketri.market.service;

import com.faketri.market.domain.product.Characteristics;
import com.faketri.market.domain.product.Product;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.impl.OrderImpl;
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
    public List<Product> findByCategories(Long categoriesId){
        return productImpl.findByCategories(categoriesId);
    }
    public List<Product> findByCharacteristics(Characteristics characteristics){
        return productImpl.findByCharacteristics(characteristics);
    }
    public Page<Product> findByCategories(Long categoriesId, Pageable pageable){
        return productImpl.findByCategories(categoriesId, pageable);
    }
    public Page<Product> findByCharacteristics(Characteristics characteristics, Pageable pageable){
        return productImpl.findByCharacteristics(characteristics, pageable);
    }
    public Page<Product> findTopSelling(Pageable pageable){
        return productImpl.findTopSelling(pageable);
    }
    public Product save(Product product){
        return productImpl.save(product);
    }
    public Boolean update(Product product){
        return productImpl.update(product);
    }
    public Boolean delete(Product product){
        return productImpl.delete(product);
    }
}

package com.faketri.market.service;

import com.faketri.market.entity.Categories;
import com.faketri.market.entity.Characteristics;
import com.faketri.market.entity.Product;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductImpl productDao;

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product findById(Long id){
        return productDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id " + id + " not found")
        );
    }

    public Page<Product> findAllByPage(Pageable pageable){
        return productDao.findAllByPage(pageable);
    }

    public List<Product> findByCategories(Categories categories){
        return productDao.findByCategories(categories);
    }

    public Page<Product> findByCharacteristics(Characteristics characteristics, Pageable pageable){
        return productDao.findByCharacteristics(characteristics, pageable);
    }
}

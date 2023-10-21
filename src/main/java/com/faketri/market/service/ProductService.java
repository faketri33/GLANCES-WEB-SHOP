package com.faketri.market.service;

import com.faketri.market.entity.Product;
import com.faketri.market.entity.enums.ECategories;
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

    public Page<Product> findByAllByPage(Pageable pageable){
        return productDao.findByAllByPage(pageable);
    }

    public List<Product> findByCategories(ECategories eCategories){
        return productDao.findByCategories(eCategories);
    }
}

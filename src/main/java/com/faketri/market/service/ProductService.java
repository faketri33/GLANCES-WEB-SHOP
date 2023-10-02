package com.faketri.market.service;

import com.faketri.market.entity.Product;
import com.faketri.market.entity.enums.ECategories;
import com.faketri.market.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> findAll() {
        return productDao.findAll();
    }
    public Product findById(Long id){
        return productDao.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
    }
    public List<Product> findByCategories(ECategories eCategories){
        return productDao.findByCategories(eCategories);
    }

    public void save(Product product){
        productDao.save(product);
    }
}

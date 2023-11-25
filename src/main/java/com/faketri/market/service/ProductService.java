package com.faketri.market.service;

import com.faketri.market.entity.Categories;
import com.faketri.market.entity.Characteristics;
import com.faketri.market.entity.Product;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                () -> new ResourceNotFoundException("Product with id " + id + " not found")
        );
    }

    public Page<Product> findAll(Pageable pageable){
        return productDao.findAll(pageable);
    }

    public List<Product> findByCategories(Categories categories){
        return null;
    }

    public List<Product> findByCharacteristics(Characteristics characteristics, Pageable pageable){
        return null;
    }

    public void save(Product product){
        productDao.save(product);
    }
}

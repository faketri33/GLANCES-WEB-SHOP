package com.faketri.market.service;

import com.faketri.market.entity.Product;
import com.faketri.market.entity.enums.ECategories;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<Product> getRecordsFromIndex(Long index) {
        return productDao.findAll(
                PageRequest.of((int)(index / 100), 100, Sort.by("id"))
        ).getContent();
    }

    public List<Product> findByCategories(ECategories eCategories){
        return productDao.findByCategories(eCategories);
    }

    public void save(Product product){
        productDao.save(product);
    }

    public void delete(Product product){ productDao.delete(product); }
}

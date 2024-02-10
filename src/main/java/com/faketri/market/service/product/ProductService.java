package com.faketri.market.service.product;

import com.faketri.market.domain.product.Characteristics;
import com.faketri.market.domain.product.Product;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productImpl;

    public List<Product> findAll() {
        return productImpl.findAll();
    }

    public Page<Product> findAll(Pageable pageable) {
        return productImpl.findAll(pageable);
    }

    public Product findById(Long id) {
        return productImpl.findById(id)
                          .orElseThrow(() -> new ResourceNotFoundException(
                                  "Product with id " + id + " not found"));
    }


    public Page<Product> findByCategories(Long categoriesId, Pageable pageable
    ) {
        return productImpl.findByCategories_Id(categoriesId, pageable);
    }

    public Page<Product> findByCharacteristics(Characteristics characteristics,
                                               Pageable pageable
    ) {
        return productImpl.findByCharacteristics_Id(characteristics.getId(),
                                                    pageable
        );
    }

    public Page<Product> findByCharacteristics(Long categoriesId,
                                               List<Long> characteristics,
                                               Pageable pageable
    ) {
        return null;
    }

    public Page<Product> findTopSelling(Pageable pageable) {
        return productImpl.findAll(PageRequest.of(pageable.getPageNumber(),
                                                  pageable.getPageSize(),
                                                  Sort.by("quantitySold")
                                                      .ascending()
        ));
    }

    public Product save(Product product) {
        return productImpl.save(product);
    }

    public int update(Product product) {
        return productImpl.update(product.getNameModel(),
                                  product.getPrice(),
                                  product.getQuantitySold(),
                                  product.getQuantity(),
                                  product.getId()
        );
    }

    public void update(Collection<Product> products) {
        products.forEach(product -> productImpl.update(product.getNameModel(),
                                                       product.getPrice(),
                                                       product.getQuantitySold(),
                                                       product.getQuantity(),
                                                       product.getId()
        ));
    }

    public void delete(Product product) {
        productImpl.delete(product);
    }

}

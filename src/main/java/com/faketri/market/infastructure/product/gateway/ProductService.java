package com.faketri.market.infastructure.product.gateway;

import com.faketri.market.entity.product.model.Product;
import com.faketri.market.entity.product.model.child.Characteristics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    Page<Product> findByCategories(Long categoriesId, Pageable pageable
    );

    Page<Product> findPromotionProduct(Pageable pageable);

    Page<Product> findTopSelling(Pageable pageable);

    Page<Product> findByCategoriesFilteredCharacteristics(Pageable pageable,
                                                          Long categoriesId,
                                                          List<Characteristics> characteristics
    );

    Product save(Product product);

    int update(Product product);

    void update(List<Product> products);

    void delete(Product product);

}

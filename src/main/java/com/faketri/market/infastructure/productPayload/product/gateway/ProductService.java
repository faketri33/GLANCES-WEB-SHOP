package com.faketri.market.infastructure.productPayload.product.gateway;

import com.faketri.market.entity.productPayload.characteristics.model.Characteristics;
import com.faketri.market.entity.productPayload.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Product findById(UUID id);

    Page<Product> findByCategories(UUID categoriesId, Pageable pageable);

    Page<Product> findPromotionProduct(Pageable pageable);

    Page<Product> findTopSelling(Pageable pageable);

    Page<Product> findByCategoriesFilteredCharacteristics(Pageable pageable,
                                                          UUID categoriesId,
                                                          List<Characteristics> characteristics
    );

    Product save(Product product);

    void delete(Product product);

}

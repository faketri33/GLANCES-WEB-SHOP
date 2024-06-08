package com.faketri.market.infastructure.product.payload.product.gateway;

import com.faketri.market.entity.product.payload.characteristics.model.Characteristics;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.infastructure.product.payload.product.dto.ProductCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Product findById(UUID id);

    List<Product> findById(List<UUID> id);

    Page<Product> findByCategories(UUID categoriesId, Pageable pageable);

    Page<Product> findPromotionProduct(Pageable pageable);

    Page<Product> findTopSelling(Pageable pageable);

    void updateQuantityAndQuantitySold(List<ProductItem> productItems);

    Page<Product> findByCategoriesFilteredCharacteristics(Pageable pageable,
                                                          UUID categoriesId,
                                                          List<Characteristics> characteristics
    );

    Page<Product> findBySearchParam(Pageable pageable, Integer minPrice, Integer maxPrice, String name, List<UUID> characteristics, UUID categoriesId);

    Integer findMaxPrice();

    Product save(Product product);

    void save(ProductCreateRequest productCreateRequest, List<MultipartFile> images);

    void update(Product product, List<MultipartFile> images);

    List<Product> save(List<Product> product);

    void delete(Product product);

    void deleteById(UUID id);

}

package com.faketri.market.usecase.product.payload.product;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.payload.characteristics.model.Characteristics;
import com.faketri.market.entity.product.payload.product.gateway.repo.ProductRepository;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.infastructure.product.payload.characteristics.gateway.CharacteristicsService;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import com.faketri.market.infastructure.product.payload.product.gateway.filter.ProductSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The type Product service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class ProductServiceImpl implements ProductService {

    static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productImpl;
    private final CharacteristicsService characteristicsService;
    private final ProductSpecification productSpecification;

    public ProductServiceImpl(ProductRepository productImpl,
                              CharacteristicsService characteristicsService,
                              ProductSpecification productSpecification) {
        this.productImpl = productImpl;
        this.characteristicsService = characteristicsService;
        this.productSpecification = productSpecification;
    }

    public List<Product> findAll() {
        return productImpl.findAll();
    }

    public Page<Product> findAll(Pageable pageable) {
        return productImpl.findAll(pageable);
    }

    public Product findById(UUID id) {
        return productImpl.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + id + " not found"));
    }

    public Page<Product> findByCategories(UUID categoriesId, Pageable pageable
    ) {
        return productImpl.findAll(productSpecification.hasCategories(
                categoriesId), pageable);
    }

    public Page<Product> findPromotionProduct(Pageable pageable) {
        return productImpl.findAll(productSpecification.isPromoItem(),
                pageable
        );
    }

    public Page<Product> findTopSelling(Pageable pageable) {
        return productImpl.findAll(PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("quantitySold").descending()
        ));
    }

    public Page<Product> findByCategoriesFilteredCharacteristics(
            Pageable pageable, UUID categoriesId,
            List<Characteristics> characteristics
    ) {
        return productImpl.findAll(productSpecification.hasCategories(categoriesId)
                .and(productSpecification.hasCharacteristics(characteristics)), pageable);
    }

    @Override
    public Page<Product> findBySearchParam(Pageable pageable, Integer minPrice, Integer maxPrice, String name, List<UUID> characteristics, UUID categoriesId) {

        Specification<Product> specification = productSpecification.likeByNameModelOrBrandName(name);

        if (characteristics != null)
            specification = specification.and(productSpecification.hasCharacteristicsByUUID(characteristics));
        if (categoriesId != null) specification = specification.and(productSpecification.hasCategories(categoriesId));

        specification.and(productSpecification.priceBetween(minPrice, maxPrice));

        return productImpl.findAll(specification, pageable);
    }

    @Override
    public Integer findMaxPrice() {
        return productImpl.findMaxPrice();
    }

    @Transactional
    public Product save(Product product) {
        product.setCharacteristics(
                product.getCharacteristics()
                        .stream()
                        .map(characteristicsService::save)
                        .collect(Collectors.toSet())
        );
        return productImpl.save(product);
    }

    @Override
    @Transactional
    public List<Product> save(List<Product> product) {
        return product.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    public void update(Product product) {
        productImpl.save(product);
    }

    public void update(List<Product> products) {
        products.forEach(productImpl::save);
    }

    public void delete(Product product) {
        productImpl.delete(product);
    }

}

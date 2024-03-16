package com.faketri.market.usecase.productPayload.product;

import com.faketri.market.MarketApplication;
import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.productPayload.characteristics.model.Characteristics;
import com.faketri.market.entity.productPayload.product.gateway.repo.ProductRepository;
import com.faketri.market.entity.productPayload.product.model.Product;
import com.faketri.market.infastructure.productPayload.characteristics.gateway.CharacteristicsService;
import com.faketri.market.infastructure.productPayload.product.gateway.ProductService;
import com.faketri.market.infastructure.productPayload.product.gateway.filter.ProductSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    static final Logger log = LoggerFactory.getLogger(MarketApplication.class);
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

    @Autowired


    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Product> findAll() {
        return productImpl.findAll();
    }

    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     */
    public Page<Product> findAll(Pageable pageable) {
        return productImpl.findAll(pageable);
    }

    /**
     * Find by id product.
     *
     * @param id the id
     * @return the product
     */
    public Product findById(UUID id) {
        return productImpl.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + id + " not found"));
    }


    /**
     * Find by categories page.
     *
     * @param categoriesId the categories id
     * @param pageable     the pageable
     * @return the page
     */
    public Page<Product> findByCategories(UUID categoriesId, Pageable pageable
    ) {
        return productImpl.findAll(productSpecification.hasCategories(
                categoriesId), pageable);
    }

    /**
     * Find promotion product page.
     *
     * @param pageable the pageable
     * @return the page
     */
    public Page<Product> findPromotionProduct(Pageable pageable) {
        return productImpl.findAll(productSpecification.isPromoItem(),
                pageable
        );
    }

    /**
     * Find top-selling page.
     *
     * @param pageable the pageable
     * @return the page
     */
    public Page<Product> findTopSelling(Pageable pageable) {
        return productImpl.findAll(PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("quantitySold")
                        .ascending()
        ));
    }

    /**
     * Find by categories filtered characteristics page.
     *
     * @param pageable        the pageable
     * @param categoriesId    the categories id
     * @param characteristics the characteristics
     * @return the page
     */
    public Page<Product> findByCategoriesFilteredCharacteristics(
            Pageable pageable, UUID categoriesId,
            List<Characteristics> characteristics
    ) {
        return productImpl.findAll(productSpecification.hasCategories(
                                categoriesId)
                        .and(productSpecification.hasCharacteristics(
                                characteristics)),
                pageable
        );
    }

    /**
     * Save product.
     *
     * @param product the product
     * @return the product
     */
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

    /**
     * Update int.
     *
     * @param product the product
     */
    public void update(Product product) {
        productImpl.save(product);
    }

    /**
     * Update.
     *
     * @param products the products
     */
    public void update(List<Product> products) {
        products.forEach(productImpl::save);
    }

    /**
     * Delete.
     *
     * @param product the product
     */
    public void delete(Product product) {
        productImpl.delete(product);
    }

}

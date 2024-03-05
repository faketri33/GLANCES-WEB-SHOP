package com.faketri.market.usecase.product;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.gateway.repo.ProductRepository;
import com.faketri.market.entity.product.model.Product;
import com.faketri.market.entity.product.model.child.Characteristics;
import com.faketri.market.infastructure.product.gateway.ProductService;
import com.faketri.market.infastructure.product.gateway.filter.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Product service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productImpl;
    private final ProductSpecification productSpecification;

    /**
     * Instantiates a new Product service.
     *
     * @param productImpl          the product repository
     * @param productSpecification the product specification
     */
    public ProductServiceImpl(ProductRepository productImpl,
                              ProductSpecification productSpecification
    ) {
        this.productImpl = productImpl;
        this.productSpecification = productSpecification;
    }

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
    public Product findById(Long id) {
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
    public Page<Product> findByCategories(Long categoriesId, Pageable pageable
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
            Pageable pageable, Long categoriesId,
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
    public Product save(Product product) {
        return productImpl.save(product);
    }

    /**
     * Update int.
     *
     * @param product the product
     * @return the int
     */
    public int update(Product product) {
        return productImpl.update(product.getNameModel(),
                product.getPrice(),
                product.getQuantitySold(),
                product.getQuantity(),
                product.getId()
        );
    }

    /**
     * Update.
     *
     * @param products the products
     */
    public void update(List<Product> products) {
        products.forEach(product -> productImpl.update(product.getNameModel(),
                product.getPrice(),
                product.getQuantitySold(),
                product.getQuantity(),
                product.getId()
        ));
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

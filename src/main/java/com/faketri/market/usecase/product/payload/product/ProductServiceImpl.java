package com.faketri.market.usecase.product.payload.product;

import com.faketri.market.entity.exception.ImageFormatException;
import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.payload.characteristics.model.Characteristics;
import com.faketri.market.entity.product.payload.product.exception.NotEnoughProductException;
import com.faketri.market.entity.product.payload.product.gateway.repo.ProductRepository;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.infastructure.product.payload.characteristics.gateway.CharacteristicsService;
import com.faketri.market.infastructure.product.payload.product.dto.ProductCreateRequest;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import com.faketri.market.infastructure.product.payload.product.gateway.filter.ProductSpecification;
import com.faketri.market.usecase.file.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
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
    private final FileUploadService fileService;

    public ProductServiceImpl(ProductRepository productImpl,
                              CharacteristicsService characteristicsService,
                              ProductSpecification productSpecification, FileUploadService fileService) {
        this.productImpl = productImpl;
        this.characteristicsService = characteristicsService;
        this.productSpecification = productSpecification;
        this.fileService = fileService;
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

    @Override
    public List<Product> findById(List<UUID> id) {
        return productImpl.findAll(productSpecification.hasId(id));
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

    @Override
    public void updateQuantityAndQuantitySold(List<ProductItem> productItems) {

        List<UUID> productIds = productItems.stream()
                .map(p -> p.getProduct().getId())
                .collect(Collectors.toList());
        List<Product> products = findById(productIds);

        Map<UUID, ProductItem> productItemMap = productItems.stream()
                .collect(Collectors.toMap(
                        p -> p.getProduct().getId(),
                        p -> p));

        products.forEach(product -> {
            ProductItem forOrder = productItemMap.get(product.getId());
            if (forOrder == null) {
                throw new ResourceNotFoundException("Продукт не найден: " + product.getId());
            }
            int quantityToBuy = forOrder.getQuantity();
            product.setQuantity(product.getQuantity() - quantityToBuy);
            product.setQuantitySold(product.getQuantitySold() + quantityToBuy);

            if (product.getQuantity() < 0) throw new NotEnoughProductException("Недостаточно товара на складе.");
        });

        save(products);
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
        if (!product.getCharacteristics().isEmpty())
            product.setCharacteristics(
                    product.getCharacteristics()
                            .stream()
                            .map(characteristicsService::save)
                            .collect(Collectors.toSet())
            );
        return productImpl.save(product);
    }

    @Override
    public void save(ProductCreateRequest productCreateRequest, List<MultipartFile> images) {
        Product product = new Product();

        product.setBrand(productCreateRequest.getBrand());
        product.setCategories(productCreateRequest.getCategories());
        product.setPrice(productCreateRequest.getPrice());
        product.setQuantity(productCreateRequest.getQuantity());
        product.setNameModel(productCreateRequest.getNameModel());
        product.setDescription(productCreateRequest.getDescription());

        product.getCharacteristics().addAll(
                productCreateRequest
                        .getCharacteristicsRequestSet()
                        .stream()
                        .map(c -> new Characteristics(null, c.getName(), c.getValue()))
                        .collect(Collectors.toSet())
        );

        product.getImage().addAll(fileService.saveImages(FileUploadService.PRODUCT_PATH, product.getNameModel(), images));

        save(product);
    }

    @Override
    public void update(Product product, List<MultipartFile> images) {
        log.info("update: " + product.toString());
        log.info("update: characteristics: " + product.getCharacteristics());

        product.setCharacteristics(
                product.getCharacteristics()
                        .stream()
                        .map(charact -> charact.getId() == null ? characteristicsService.save(charact) : charact)
                        .collect(Collectors.toSet())
        );

        final List<Image> image = fileService.saveImages(
                FileUploadService.PRODUCT_PATH,
                product.getNameModel(),
                images
        );

        if (image != null) product.getImage().addAll(image);
        save(product);
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

    @Override
    public void deleteById(UUID id) {
        productImpl.deleteById(id);
    }

}

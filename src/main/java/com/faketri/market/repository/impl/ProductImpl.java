package com.faketri.market.repository.impl;

import com.faketri.market.entity.Brand;
import com.faketri.market.entity.Characteristics;
import com.faketri.market.entity.Product;
import com.faketri.market.repository.ProductRepository;
import com.faketri.market.repository.impl.mapper.ProductExtractor;
import com.faketri.market.repository.impl.mapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class ProductImpl implements ProductRepository {

    private final String basicSelectSQl =
            "SELECT p.id, p.name_model, p.categories_id, p.price, p.quantity, p.quntitysold, p.is_promotion, " +
            "p.promotion_price, brand.id as brand_id, brand.name as brand_name, i.id AS image_id, i.image, " +
            "c.name as categories_name " +
            "FROM product p " +
            "LEFT JOIN product_image pi ON p.id = pi.product_id " +
            "LEFT JOIN image i ON pi.image_id = i.id " +
            "LEFT JOIN categories c on c.id = p.categories_id " +
            "LEFT JOIN brand on brand.id = p.brand_id ";

    @Autowired
    private NamedParameterJdbcTemplate template;

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(template.queryForObject(
                basicSelectSQl + "WHERE p.id = :id",
                    Map.of("id", id),
                    new ProductRowMapper())
        );
    }
    public List<Product> findAll(){
        return template.query(
                basicSelectSQl,
                new ProductExtractor()
            );
    }
    @Override
    public List<Product> findByBrand(Brand brand) {
        return template.query(
                basicSelectSQl + " WHERE brand.name = :brandName",
                Map.of("brandName", brand.getName()),
                new ProductExtractor()
        );
    }
    public List<Product> findByCategories(Long categoriesId){
        return template.query(
                basicSelectSQl + "WHERE pcat.categories_id = :categories ",
                    Map.of("categories", categoriesId),
                new ProductExtractor());
    }
    public List<Product> findByCharacteristics(Characteristics characteristics) {
        return template.query(
                basicSelectSQl + "WHERE ch.id = :characteristics ",
                Map.of("characteristics", characteristics.getId()),
                new ProductExtractor());
    }
    @Override
    public Page<Product> findByBrand(Brand brand, Pageable pageable) {
        return new PageImpl<>(
                Objects.requireNonNull(template.query(
                        basicSelectSQl + " WHERE brand.name = :brandName" +
                                "LIMIT " + pageable.getPageSize() + " " +
                                "OFFSET " + pageable.getOffset(),
                        Map.of("brandName", brand.getName()),
                        new ProductExtractor())
                ), pageable, countByBrand(brand));
    }
    public Page<Product> findAll(Pageable pageable){
        return new PageImpl<>(
                Objects.requireNonNull(template.query(
                        basicSelectSQl +
                                "LIMIT " + pageable.getPageSize() + " " +
                                "OFFSET " + pageable.getOffset(),
                        new ProductExtractor()
                )), pageable, countAll());
    }
    public Page<Product> findByCharacteristics(Characteristics characteristics, Pageable pageable) {
        return new PageImpl<>(
                Objects.requireNonNull(
                        template.query(
                        basicSelectSQl + "WHERE ch.id = :characteristics " +
                                       "LIMIT " + pageable.getPageSize() + " " +
                                       "OFFSET " + pageable.getOffset(),
                        Map.of("characteristics", characteristics.getId()),
                        new ProductExtractor())
                ), pageable, countByCharacteristics(characteristics));
    }
    @Override
    public Page<Product> findByCharacteristics(List<Characteristics> characteristics, Pageable pageable) {
        return null; // TODO :: FIND BY CHARACTERISTICS
    }
    public  Page<Product> findByCategories(Long categoriesId, Pageable pageable) {
        return new PageImpl<>(Objects.requireNonNull(template.query(
                basicSelectSQl + "WHERE pcat.categories_id = :categories "+
                        "LIMIT " + pageable.getPageSize() + " " +
                        "OFFSET " + pageable.getOffset(),
                Map.of("categories", categoriesId),
                new ProductExtractor())
        ), pageable, countByCategories(categoriesId));
    }
    @Override
    public Page<Product> findTopSelling(Pageable pageable) {
        return new PageImpl<>(Objects.requireNonNull(template.query(
                basicSelectSQl + "ORDER BY quntitysold desc;"+
                        "LIMIT " + pageable.getPageSize() + " " +
                        "OFFSET " + pageable.getOffset(),
                new ProductExtractor())
        ), pageable, countAll());
    }
    public Long save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
            "insert into product(brand_id, name_model, price, quantity, quntitysold, categories_id) " +
                "values (:brand_id, :name_model, :price, :quantity, :quntitysold, :categories_id);",
                new MapSqlParameterSource(Map.of(
                        "brand_id", product.getBrand().getId(),
                        "name_model", product.getNameModel(),
                        "price", product.getPrice(),
                        "quantity", product.getQuantity(),
                        "quntitysold", product.getQuantitySold(),
                        "categories_id", product.getCategories().getId()
                    )
                ), keyHolder, new String[] {"id"});

        Long productId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        product.getImage().forEach(x ->
                template.update("insert into product_image(image_id, product_id) " +
                                "values(:imagesId, :productId)",
                    Map.of("imagesId", x.getId(),"productId", productId)
                )
        );
        product.getCharacteristics().forEach(x ->
                template.update("insert into product_characteristics(characteristics_id, product_id) " +
                                "values(:characteristics_id, :productId)",
                        Map.of("characteristics_id", x.getId(),"productId", productId)
                )
        );

        return productId;
    }
    public Boolean update(Product product) {
        return template.update(
                "update product " +
                    "set brand_id = :brand_id, " +
                    "name_model = :name_model, " +
                    "price = :price, " +
                    "quantity = :quantity, " +
                    "quantitySold = :quntitysold, " +
                    "is_promotion = :is_promotion," +
                    "promotion_price = :promotion_price " +
                    "where product.id = :id",
                Map.of(
                    "id", product.getId(),
                    "brand_id", product.getBrand().getId(),
                    "name_model", product.getNameModel(),
                    "price", product.getPrice(),
                    "quantity", product.getQuantity(),
                    "quntitysold", product.getQuantitySold(),
                    "is_promotion", product.getIsPromotion(),
                    "promotion_price", product.getPromotionPrice()
                )
        ) > 0;
    }
    public Boolean delete(Product product) {
        return template.update(
                "delete from product where product.id = :id",
                Map.of(
                        "id", product.getId()
                )
        ) > 0;
    }
    public int countAll(){
        return template.query("select COUNT(*) from product",
                (rs, rowNum) -> rs.getInt(1)).get(0);
    }
    public int countByCategories(Long categoriesId){
        return template.query("select COUNT(*) from product_categories pc where pc.categories_id = :id",
                        Map.of("id", categoriesId),
                        (rs, rowNum) -> rs.getInt(1))
                .get(0);
    }
    public int countByCharacteristics(Characteristics characteristics){
        return template.query("select COUNT(*) from product_characteristics pc where pc.id = :id ",
                        Map.of("id", characteristics.getId()),
                        (rs, rowNum) -> rs.getInt(1))
                .get(0);
    }
    public int countByBrand(Brand brand){
        return template.query("select count(*) from product where brand.id = :id",
                        Map.of("id", brand.getId()),
                        (rs, rowNum) -> rs.getInt(1))
                .get(0);
    }
}

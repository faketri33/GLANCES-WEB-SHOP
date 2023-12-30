package com.faketri.market.repository.impl;

import com.faketri.market.domain.product.Brand;
import com.faketri.market.domain.product.Characteristics;
import com.faketri.market.domain.product.Product;
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

import java.sql.Types;
import java.util.*;


@Repository
public class ProductImpl implements com.faketri.market.repository.Repository<Long, Product> {

    private final String basicSelectSQl =
            "select p.*, b.name as brand_name, c.name as categories_name, " +
                "i.id as image_id, i.image, ch.id as characteristics_id, ch.name as characteristics_name, " +
                "ch.value as characteristics_value " +
            "from product p " +
                "left join brand b on b.id = p.brand_id " +
                "left join categories c on c.id = p.categories_id " +
                "left join product_image pi on pi.product_id = p.id " +
                "left join image i on i.id = pi.image_id " +
                "left join product_characteristics pc on pc.product_id = p.id " +
                "left join characteristics ch on ch.id = pc.characteristics_id ";

    @Autowired
    private NamedParameterJdbcTemplate template;

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(template.queryForObject(
                basicSelectSQl + "WHERE p.id = :id",
                    Map.of("id", id),
                    new ProductRowMapper())
        );
    }

    @Override
    public Product findByFields(Product entity) {
        return null;
    }

    public List<Product> findAll(){
        return template.query(
                basicSelectSQl,
                new ProductExtractor()
            );
    }
    public List<Product> findByBrand(Brand brand) {
        return template.query(
                basicSelectSQl + " WHERE p.brand_id = :brand_id",
                Map.of("brand_id", brand.getId()),
                new ProductExtractor()
        );
    }
    public List<Product> findByCategories(Long categoriesId){
        return template.query(
                basicSelectSQl + "WHERE p.categories_id = :categories ",
                    Map.of("categories", categoriesId),
                new ProductExtractor());
    }
    public List<Product> findByCharacteristics(Characteristics characteristics) {
        return template.query(
                basicSelectSQl + "WHERE ch.id = :characteristics ",
                Map.of("characteristics", characteristics.getId()),
                new ProductExtractor());
    }
    public Page<Product> findByBrand(Brand brand, Pageable pageable) {
        return new PageImpl<>(
                Objects.requireNonNull(template.query(
                        basicSelectSQl + " WHERE p.brand_id = :brand_id" +
                                "LIMIT " + pageable.getPageSize() + " " +
                                "OFFSET " + pageable.getOffset(),
                        Map.of("brand_id", brand.getId()),
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
    public Page<Product> findByCharacteristics(List<Characteristics> characteristics, Pageable pageable) {
        return null; // TODO :: FIND BY CHARACTERISTICS
    }
    public  Page<Product> findByCategories(Long categoriesId, Pageable pageable) {
        return new PageImpl<>(Objects.requireNonNull(template.query(
                basicSelectSQl + "WHERE p.categories_id = :categories "+
                        "LIMIT " + pageable.getPageSize() + " " +
                        "OFFSET " + pageable.getOffset(),
                Map.of("categories", categoriesId),
                new ProductExtractor())
        ), pageable, countByCategories(categoriesId));
    }
    public Page<Product> findTopSelling(Pageable pageable) {
        return new PageImpl<>(Objects.requireNonNull(template.query(
                basicSelectSQl + "ORDER BY quantity_sold desc;"+
                        "LIMIT " + pageable.getPageSize() + " " +
                        "OFFSET " + pageable.getOffset(),
                new ProductExtractor())
        ), pageable, countAll());
    }
    public Product save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = getMapSqlParameterSource(product);
        template.update(
            "insert into product(brand_id, name_model, price, quantity, quantity_sold, " +
                    "is_promo_active, promotion_price, discount, categories_id) " +
                "values (:brand_id, :name_model, :price, :quantity, :quantity_sold, " +
                    ":is_promo_active, :promotion_price, :discount, :categories_id);",
                sqlParameterSource, keyHolder, new String[] {"id"});

        product.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        product.getImage().forEach(x ->
                template.update("insert into product_image(image_id, product_id) " +
                                "values(:imagesId, :productId)",
                    Map.of("imagesId", x.getId(),"productId", product.getId())
                )
        );
        product.getCharacteristics().forEach(x ->
                template.update("insert into product_characteristics(characteristics_id, product_id) " +
                                "values(:characteristics_id, :productId)",
                        Map.of("characteristics_id", x.getId(),"productId", product.getId())
                )
        );

        return product;
    }

    public Boolean update(Product product) {
        return template.update(
                "update product " +
                    "set brand_id = :brand_id, " +
                    "name_model = :name_model, " +
                    "price = :price, " +
                    "quantity = :quantity, " +
                    "quantity_sold = :quantity_sold, " +
                    "is_promo_active = :is_promo_active, " +
                    "promotion_price = :promotion_price, " +
                    "discount = :discount, " +
                    "categories_id = :categories_id " +
                    "where product.id = :id",
                getMapSqlParameterSource(product)
        ) > 0;
    }
    public Boolean delete(Product product) {
        return template.update(
                "delete from product where product.id = :id",
                Map.of("id", product.getId())
        ) > 0;
    }



    public int countAll(){
        return template.query("select COUNT(*) from product",
                (rs, rowNum) -> rs.getInt(1)).get(0);
    }
    public int countByCategories(Long categoriesId){
        return template.query("select COUNT(*) from product p where p.categories_id = :id",
                        Map.of("id", categoriesId),
                        (rs, rowNum) -> rs.getInt(1)).get(0);
    }
    public int countByCharacteristics(Characteristics characteristics){
        return template.query("select COUNT(*) from product_characteristics pc where pc.characteristics_id = :id ",
                        Map.of("id", characteristics.getId()),
                        (rs, rowNum) -> rs.getInt(1)).get(0);
    }
    public int countByBrand(Brand brand){
        return template.query("select count(*) from product where brand_id = :id",
                        Map.of("id", brand.getId()),
                        (rs, rowNum) -> rs.getInt(1)).get(0);
    }

    private MapSqlParameterSource getMapSqlParameterSource(Product product) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource(
                Map.of("brand_id", product.getBrand().getId(),
                        "name_model", product.getNameModel(),
                        "price", product.getPrice(),
                        "quantity", product.getQuantity(),
                        "quantity_sold", product.getQuantitySold(),
                        "promotion_price", product.getPromoPrice(),
                        "discount", product.getDiscount(),
                        "categories_id", product.getCategories().getId())
        );
        sqlParameterSource.addValue("is_promo_active", product.getIsPromoActive());
        return sqlParameterSource;
    }
}

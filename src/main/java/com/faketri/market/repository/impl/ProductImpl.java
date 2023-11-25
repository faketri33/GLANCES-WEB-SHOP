package com.faketri.market.repository.impl;

import com.faketri.market.entity.Categories;
import com.faketri.market.entity.Characteristics;
import com.faketri.market.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;


public class ProductImpl {

    private final String basicSQl =
            "SELECT p.id, p.brand, p.name_model, p.price, i.id AS image_id, i.photo, " +
            "r.id as rating_id, r.description, r.grade, ch.id as characteristics_id, ch.name, ch.value " +
            "FROM product p " +
            "LEFT JOIN product_image pi ON p.id = pi.product_id " +
            "LEFT JOIN image i ON pi.image_id = i.id " +
            "LEFT JOIN product_rating pr on p.id = pr.product_id " +
            "LEFT JOIN rating r on pr.rating_id = r.id " +
            "LEFT JOIN product_characteristics pc on p.id = pc.product_id " +
            "LEFT JOIN characteristics ch on ch.id = pc.characteristics_id ";

    @Autowired
    private NamedParameterJdbcTemplate template;

    /*public List<Product> findAll(){
        return template.query(
                basicSQl,
                new ProductExtractor()
            );
    }

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(template.queryForObject(
                basicSQl + "WHERE p.id = :id",
                    Map.of("id", id),
                    new ProductRowMapper())
        );
    }

    public Page<Product> findAllByPage(Pageable pageable){

        int total = template.query("select COUNT(*) from product",
                (rs, rowNum) -> rs.getInt(1)).get(0);

        return new PageImpl<>(
                Objects.requireNonNull(template.query(
                        basicSQl +
                            "LIMIT " + pageable.getPageSize() + " " +
                            "OFFSET " + pageable.getOffset(),
                        new ProductExtractor()
                )), pageable, total);
    }


    public List<Product> findByCategories(Categories eCategories){
        return template.query(
                basicSQl + "WHERE pc.categories = :categories ",
                    Map.of("categories", eCategories),
                new ProductExtractor());
    }

    @Override
    public List<Product> findByCharacteristics(Characteristics characteristics) {
        return template.query(
                basicSQl + "WHERE ch.id = :characteristics ",
                Map.of("characteristics", characteristics.getId()),
                new ProductExtractor());
    }

    public Page<Product> findByCharacteristics(Characteristics characteristics, Pageable pageable) {
        int total = template.query("select COUNT(*) from product",
                (rs, rowNum) -> rs.getInt(1)).get(0);

        return new PageImpl<>(
                Objects.requireNonNull(
                        template.query(
                        basicSQl + "WHERE ch.id = :characteristics " +
                                       "LIMIT " + pageable.getPageSize() + " " +
                                       "OFFSET " + pageable.getOffset(),
                        Map.of("characteristics", characteristics.getId()),
                        new ProductExtractor())
                ), pageable, total);
    }

    public  Page<Product> findByCategories(Categories categories, Pageable pageable) {
        int total = template.query("select COUNT(*) from product",
                (rs, rowNum) -> rs.getInt(1)).get(0);

        return new PageImpl<>(Objects.requireNonNull(template.query(
                basicSQl + "WHERE pc.categories = :categories "+
                        "LIMIT " + pageable.getPageSize() + " " +
                        "OFFSET " + pageable.getOffset(),
                Map.of("categories", categories),
                new ProductExtractor())
        ), pageable, total);
    }*/

    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    public List<Product> findAll() {
        return null;
    }


    public Page<Product> findAllByPage(Pageable pageable) {
        return null;
    }

    public void save(Product entity) {

    }

    public void update(Product entity) {

    }

    public void delete(Product entity) {

    }

    public List<Product> findByCategories(Categories categories) {
        return null;
    }

    public List<Product> findByCharacteristics(Characteristics characteristics) {
        return null;
    }

    // TODO : search by price range
    //List<Product> findByPriceFromStartBeforeStop(double start, double stop);
}

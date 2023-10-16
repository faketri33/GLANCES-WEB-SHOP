package com.faketri.market.repository;

import com.faketri.market.entity.Characteristics;
import com.faketri.market.entity.Image;
import com.faketri.market.entity.Product;
import com.faketri.market.entity.Rating;
import com.faketri.market.entity.enums.EBrand;
import com.faketri.market.entity.enums.ECategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductDao{
    @Autowired
    private NamedParameterJdbcTemplate template;

    public List<Product> findAll(){
        return template.query(
                "SELECT p.id, p.brand, p.name_model, p.price, i.id AS image_id, i.photo " +
                    "FROM product p " +
                    "LEFT JOIN product_image pi ON p.id = pi.product_id " +
                    "LEFT JOIN image i ON pi.image_id = i.id",
                rs -> {
                    Map<Long, Product> productMap = new HashMap<>();
                    while (rs.next()) {
                        Long id = rs.getLong("id");
                        Product product = productMap.get(id);

                        if(product == null)
                            product = Product.newBuilder()
                                    .setId(id)
                                    .setBrand(EBrand.valueOf(rs.getString("brand")))
                                    .setNameModel(rs.getString("name_model"))
                                    .setPrice(rs.getDouble("price"))
                                    .build();

                        product.Builder()
                                .addImage(
                                        new Image(rs.getLong("image_id"),
                                        rs.getBytes("photo")))
                                .build();

                        productMap.put(id, product);
                    }
                    return new ArrayList<>(productMap.values());
                }
        );
    }

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(
                template.query(
                    "SELECT p.id, p.brand, p.name_model, p.price, i.id AS image_id, i.photo, " +
                        "r.id as rating_id, r.description, r.grade " +
                        "FROM product p " +
                        "LEFT JOIN product_image pi ON p.id = pi.product_id " +
                        "LEFT JOIN image i ON pi.image_id = i.id " +
                        "LEFT JOIN product_rating pr on p.id = pr.product_id " +
                        "LEFT JOIN rating r on pr.rating_id = r.id " +
                        "LEFT JOIN product_characteristics pc on p.id = pc.product_id " +
                        "LEFT JOIN characteristics ch on ch.id = pc.characteristics_id " +
                        "WHERE p.id = :id",
                        Map.of("id", id),
                        rs -> {
                            Product product = new Product(
                                    rs.getLong("id"),
                                    rs.getString("brand"),
                                    rs.getString("name_model"),
                                    rs.getDouble("price")
                            );

                            while (rs.next()) {
                                product.Builder()
                                        .addImage(
                                                new Image(rs.getLong("image_id"),
                                                        rs.getBytes("photo")))
                                        .addRating(new Rating(rs.getLong("rating_id"),
                                                rs.getString("description"),
                                                rs.getShort("grade")))
                                        .addCharacteristics(
                                                new Characteristics(rs.getLong("characteristics_id"),
                                                        rs.getString("name"),
                                                        rs.getString("value")))
                                        .build();
                            }
                            return product;
                        })
        );
    }

    public Page<Product> findByAllByPage(Pageable pageable){

        int total = template.query("select COUNT(*) from product",
                (rs, rowNum) -> rs.getInt(1)).get(0);

        return template.query(
                        "SELECT p.id, p.brand, p.name_model, p.price, i.id AS image_id, i.photo, " +
                            "r.id as rating_id, r.description, r.grade, ch.id as characteristics_id, ch.name, ch.value " +
                                "FROM product p " +
                            "LEFT JOIN product_image pi ON p.id = pi.product_id " +
                            "LEFT JOIN image i ON pi.image_id = i.id " +
                            "LEFT JOIN product_rating pr on p.id = pr.product_id " +
                            "LEFT JOIN rating r on pr.rating_id = r.id " +
                            "LEFT JOIN product_characteristics pc on p.id = pc.product_id " +
                            "LEFT JOIN characteristics ch on ch.id = pc.characteristics_id " +
                            "LIMIT " + pageable.getPageSize() + " " +
                            "OFFSET " + pageable.getOffset(),
                        rs -> {
                            Map<Long, Product> productMap = new HashMap<>();

                            while (rs.next()) {
                                Long id = rs.getLong("id");
                                Product product = productMap.get(id);

                                if(product == null)
                                    product = Product.newBuilder()
                                            .setId(id)
                                            .setBrand(EBrand.valueOf(rs.getString("brand")))
                                            .setNameModel(rs.getString("name_model"))
                                            .setPrice(rs.getDouble("price"))
                                            .build();

                                product.Builder()
                                        .addImage(
                                                new Image(rs.getLong("image_id"),
                                                        rs.getBytes("photo")))
                                        .addRating(new Rating(rs.getLong("rating_id"),
                                                rs.getString("description"),
                                                rs.getShort("grade")))
                                        .addCharacteristics(
                                                new Characteristics(rs.getLong("characteristics_id"),
                                                        rs.getString("name"),
                                                        rs.getString("value")))
                                        .build();

                                productMap.put(id, product);
                            }

                            return new PageImpl<Product>(new ArrayList<>(productMap.values()), pageable, total);
                        }
        );
    }

    public List<Product> findByCategories(ECategories eCategories){
        return null;
    }

    // TODO : search by price range
    //List<Product> findByPriceFromStartBeforeStop(double start, double stop);
}

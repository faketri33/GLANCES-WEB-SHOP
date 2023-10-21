package com.faketri.market.repository;

import com.faketri.market.entity.Product;
import com.faketri.market.entity.enums.ECategories;
import com.faketri.market.repository.Contract.ProductDao;
import com.faketri.market.repository.Contract.RepositoryDao;
import com.faketri.market.repository.jdbcEntiryLiner.extractor.ProductExtractor;
import com.faketri.market.repository.jdbcEntiryLiner.mapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductImpl implements RepositoryDao<Product>, ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public List<Product> findAll(){
        return template.query(
                "SELECT p.id, p.brand, p.name_model, p.price, i.id AS image_id, i.photo " +
                    "FROM product p " +
                    "LEFT JOIN product_image pi ON p.id = pi.product_id " +
                    "LEFT JOIN image i ON pi.image_id = i.id",
                    new ProductExtractor()
        );
    }

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(template.queryForObject(
                "SELECT p.id, p.brand, p.name_model, p.price, i.id AS image_id, i.photo, " +
                    "r.id as rating_id, r.description, r.grade, ch.id as characteristics_id, ch.name, ch.value " +
                    "FROM product p " +
                    "LEFT JOIN product_image pi ON p.id = pi.product_id " +
                    "LEFT JOIN image i ON pi.image_id = i.id " +
                    "LEFT JOIN product_rating pr on p.id = pr.product_id " +
                    "LEFT JOIN rating r on pr.rating_id = r.id " +
                    "LEFT JOIN product_characteristics pc on p.id = pc.product_id " +
                    "LEFT JOIN characteristics ch on ch.id = pc.characteristics_id " +
                    "WHERE p.id = :id",
                    Map.of("id", id),
                    new ProductRowMapper())
        );
    }

    public Page<Product> findByAllByPage(Pageable pageable){

        int total = template.query("select COUNT(*) from product",
                (rs, rowNum) -> rs.getInt(1)).get(0);

        return new PageImpl<>(
                Objects.requireNonNull(template.query(
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
                        new ProductExtractor()
                )), pageable, total);
    }

    @Override
    public void save(Product entity) {

    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(Product entity) {

    }

    public List<Product> findByCategories(ECategories eCategories){
        return template.query(
                "SELECT p.id, p.brand, p.name_model, p.price, i.id AS image_id, i.photo " +
                        "FROM product p " +
                        "LEFT JOIN product_image pi ON p.id = pi.product_id " +
                        "LEFT JOIN image i ON pi.image_id = i.id " +
                        "LEFT JOIN product_categories pc on pc.product id = p.id " +
                        "WHERE pc.categories = :categories ",
                    Map.of("categories", eCategories),
                new ProductExtractor());
    }

    // TODO : search by price range
    //List<Product> findByPriceFromStartBeforeStop(double start, double stop);
}

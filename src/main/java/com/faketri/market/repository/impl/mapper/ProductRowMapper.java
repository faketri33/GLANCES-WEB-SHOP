package com.faketri.market.repository.impl.mapper;

import com.faketri.market.domain.image.Image;
import com.faketri.market.domain.product.Brand;
import com.faketri.market.domain.product.Categories;
import com.faketri.market.domain.product.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product(rs.getLong("id"),
                new Brand(rs.getLong("brand_id"), rs.getString("brand_name")),
                rs.getString("name_model"),
                rs.getLong("price"),
                rs.getInt("quantity"),
                rs.getInt("quntitysold"),
                rs.getBoolean("is_promotion"),
                rs.getLong("promotion_price")
        );
        product.setCategories(new Categories(rs.getLong("categories_id"),
                rs.getString("categories_name")));

        do {
            product.addImage(
                        new Image(rs.getLong("image_id"),
                                rs.getBytes("image")
                        ));
        } while (rs.next());

        return product;
    }
}

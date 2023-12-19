package com.faketri.market.repository.impl.mapper;

import com.faketri.market.entity.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.parameters.P;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

public class PromotionRowMapper implements RowMapper<Promotion> {
    @Override
    public Promotion mapRow(ResultSet rs, int rowNum) throws SQLException {

        HashMap<Long, Product> productHashMap = new HashMap<>();
        Promotion promotion = new Promotion(rs.getLong("promotion_id"), rs.getBytes("banner"),
                rs.getString("title"), rs.getString("discription"),
                rs.getTimestamp("date_of_start").toLocalDateTime(),
                rs.getTimestamp("date_of_end").toLocalDateTime(), new HashSet<>());

        do {
            Long productId = rs.getLong("id");
            Product product = productHashMap.get(productId);
            if (product == null) {
                product = new Product(productId,
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
            }
            product.addImage(
                    new Image(rs.getLong("image_id"),
                            rs.getBytes("image")
                    ));

            productHashMap.put(productId, product);
        } while (rs.next());

        promotion.getProducts().putAll(0, productHashMap.values());

        return promotion;
    }
}

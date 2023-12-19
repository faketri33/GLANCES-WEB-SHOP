package com.faketri.market.repository.impl.mapper;

import com.faketri.market.entity.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PromotionExtractor implements ResultSetExtractor<List<Promotion>> {
    @Override
    public List<Promotion> extractData(ResultSet rs) throws SQLException, DataAccessException {

        HashMap<Long, Promotion> promotionHashMap = new HashMap<>();
        while(rs.next()) {
            HashMap<Long, Product> productHashMap = new HashMap<>();
            Long promotionId = rs.getLong("promotion_id");
            Promotion promotion = promotionHashMap.get(promotionId);
            if(promotion == null)
                promotion = new Promotion(promotionId, rs.getBytes("banner"),
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
            promotion.getProducts().addAll(productHashMap.values());
            promotionHashMap.put(promotion.getId(), promotion);
        }

        return null;
    }
}

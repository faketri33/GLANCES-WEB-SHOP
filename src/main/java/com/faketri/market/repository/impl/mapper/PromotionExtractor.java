package com.faketri.market.repository.impl.mapper;

import com.faketri.market.entity.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PromotionExtractor implements ResultSetExtractor<Collection<Promotion>> {
    @Override
    public Collection<Promotion> extractData(ResultSet rs) throws SQLException, DataAccessException {

        HashMap<Long, Promotion> promotionHashMap = new HashMap<>();

       while (rs.next()){

            Long promotionId = rs.getLong("promotion_id");
            Long productId = rs.getLong("product_id");
            Promotion promotion = promotionHashMap.get(promotionId);
            int discount = rs.getInt("promotion_item_discount");
            if(promotion == null){ promotion = new Promotion(promotionId,
                                                rs.getBytes("promotion_banner"),
                                                rs.getString("promotion_title"),
                                                rs.getString("promotion_discription"),
                                                rs.getTimestamp("promotion_start").toLocalDateTime(),
                                                rs.getTimestamp("promotion_end").toLocalDateTime() );
                promotionHashMap.put(promotionId, promotion);
            }

           PromotionItem promotionItem = promotionHashMap.get(promotionId).getProducts().stream()
                   .filter(item -> Objects.equals(item.getProduct().getId(), productId))
                   .findFirst().orElseGet(() -> {
                       PromotionItem promotionItem1 = new PromotionItem();
                       try {
                           promotionItem1.setProduct(
                                   new Product(rs.getLong("product_id"),
                                       new Brand(rs.getLong("brand_id"),
                                               rs.getString("brand_name")),
                                               rs.getString("product_name_model"),
                                               rs.getLong("product_price"),
                                               rs.getInt("product_quantity"),
                                               rs.getInt("product_quantitysold"),
                                               rs.getBoolean("product_is_promotion"),
                                               rs.getLong("product_promotion_price")
                           ));
                       } catch (SQLException e) { throw new RuntimeException(e); };
                       promotionItem1.setDiscount(discount);
                       return promotionItem1;
                   });

           promotionItem.getProduct().setCategories(
                   new Categories(rs.getLong("categories_id"),
                        rs.getString("categories_name")
                   ));
           promotionItem.getProduct().addImage(
                   new Image(rs.getLong("image_id"),
                           rs.getBytes("image")
                   ));
           promotionItem.getProduct().getCharacteristics().add(
                   new Characteristics(rs.getLong("characterostics_id"),
                           rs.getString("characterostics_name"),
                           rs.getString("characterostics_value")
                   ));
           promotionItem.getProduct().getRating().add(
                   new Rating(rs.getLong("rating_id"),
                           rs.getString("rating_description"),
                           rs.getByte("rating_grate"), productId,
                           rs.getLong("user_id")));

           promotion.getProducts().add(promotionItem);
       }

        return promotionHashMap.values();
    }
}

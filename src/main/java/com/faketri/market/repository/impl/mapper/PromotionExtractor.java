package com.faketri.market.repository.impl.mapper;

import com.faketri.market.domain.image.Image;
import com.faketri.market.domain.product.Brand;
import com.faketri.market.domain.product.Categories;
import com.faketri.market.domain.product.Characteristics;
import com.faketri.market.domain.product.Product;
import com.faketri.market.domain.promo.Promotion;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionExtractor implements ResultSetExtractor<List<Promotion>> {

    @Override
    public List<Promotion> extractData(ResultSet rs) throws SQLException,
                                                            DataAccessException {

        Map<Long, Promotion> promotionHashMap = new HashMap<>();
        Map<Long, Product> products = new HashMap<>();
        Long lastId = null;

        while (rs.next()) {
            Long promotionId = rs.getLong("promotion_id");
            if (lastId == null) lastId = promotionId;
            Promotion promotion = promotionHashMap.get(promotionId);
            if (!promotionId.equals(lastId)) {
                promotionHashMap.get(lastId)
                                .getPromotionItems()
                                .addAll(products.values());
                products.clear();
                lastId = promotionId;
            }
            if (promotion == null) promotion = new Promotion(promotionId,
                                                             rs.getBytes(
                                                                     "banner"),
                                                             rs.getString(
                                                                     "title"),
                                                             rs.getString(
                                                                     "description"),
                                                             rs.getTimestamp(
                                                                       "date_of_start")
                                                               .toLocalDateTime(),
                                                             rs.getTimestamp(
                                                                       "date_of_end")
                                                               .toLocalDateTime()
            );

            promotionHashMap.put(promotionId, promotion);
            // ----------------- Product ------------------------
            Long productId = rs.getLong("id");
            Product product = products.get(productId);

            if (product == null) {
                product = new Product(rs.getLong("id"),
                                      new Brand(rs.getLong("brand_id"),
                                                rs.getString("brand_name")
                                      ),
                                      rs.getString("name_model"),
                                      new Categories(rs.getLong("categories_id"),
                                                     rs.getString(
                                                             "categories_name"),
                                                     rs.getBytes(
                                                             "categories_image")
                                      ),
                                      rs.getLong("price"),
                                      rs.getBoolean("is_promo_active"),
                                      rs.getLong("promotion_price"),
                                      rs.getInt("discount"),
                                      rs.getInt("quantity"),
                                      rs.getInt("quantity_sold")
                );
            }
            product.getImage()
                   .add(new Image(rs.getLong("image_id"),
                                  rs.getBytes("image")
                   ));
            product.getCharacteristics()
                   .add(new Characteristics(rs.getLong("characteristics_id"),
                                            rs.getString("characteristics_name"),
                                            rs.getString("characteristics_value")
                   ));

            products.put(productId, product);
        }
        if (lastId != null) promotionHashMap.get(lastId)
                                            .getPromotionItems()
                                            .addAll(products.values());

        return new ArrayList<>(promotionHashMap.values());
    }

}

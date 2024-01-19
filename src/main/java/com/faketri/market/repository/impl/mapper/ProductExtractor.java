package com.faketri.market.repository.impl.mapper;

import com.faketri.market.domain.image.Image;
import com.faketri.market.domain.product.Brand;
import com.faketri.market.domain.product.Categories;
import com.faketri.market.domain.product.Characteristics;
import com.faketri.market.domain.product.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductExtractor implements ResultSetExtractor<List<Product>> {

    @Override
    public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
        HashMap<Long, Product> products = new HashMap<>();

        while (rs.next()){
           Long id = rs.getLong("id");
           Product product = products.get(id);

            if(product == null){
                product = new Product(
                        rs.getLong("id"),
                        new Brand(rs.getLong("brand_id"), rs.getString("brand_name")),
                        rs.getString("name_model"),
                        new Categories(rs.getLong("categories_id"),
                                rs.getString("categories_name"), rs.getBytes("categories_image")),
                        rs.getLong("price"),
                        rs.getBoolean("is_promo_active"),
                        rs.getLong("promotion_price"),
                        rs.getInt("discount"),
                        rs.getInt("quantity"),
                        rs.getInt("quantity_sold")
                );
            }
            product.getImage().add(
                    new Image(rs.getLong("image_id"), rs.getBytes("image"))
            );
            product.getCharacteristics().add(
                    new Characteristics(rs.getLong("characteristics_id"),
                            rs.getString("characteristics_name"),
                            rs.getString("characteristics_value") )
            );

           products.put(id, product);
        };

       return new ArrayList<>(products.values());
    }
}

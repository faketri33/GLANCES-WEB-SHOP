package com.faketri.market.repository.impl.mapper;

import com.faketri.market.entity.*;
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
                product = new Product(id,
                        new Brand(rs.getLong("brand_id"), rs.getString("brand_name")),
                        rs.getString("name_model"),
                        rs.getLong("price"),
                        rs.getInt("quantity"),
                        rs.getInt("quntitysold")
                );
            }
            product.setCategories(new Categories(rs.getLong("categories_id"),
                    rs.getString("categories_name")));

            product.addImage(
                    new Image(rs.getLong("image_id"),
                            rs.getBytes("image")
                    ));
            product.addCharacteristics(
                    new Characteristics(rs.getLong("characteristics_id"),
                            rs.getString("ch_name"),
                            rs.getString("ch_value")
                    ));

           products.put(id, product);
        };

       return new ArrayList<>(products.values());
    }
}

package com.faketri.market.repository.jdbcEntiryLiner.extractor;

import com.faketri.market.entity.Characteristics;
import com.faketri.market.entity.Image;
import com.faketri.market.entity.Product;
import com.faketri.market.entity.Rating;
import com.faketri.market.entity.enums.EBrand;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductExtractor implements ResultSetExtractor<List<Product>> {
    @Override
    public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Product> productMap = new HashMap<>();

        while (rs.next()) {
            Long id = rs.getLong("id");
            Product product = productMap.get(id);

            int columnCount = rs.getMetaData().getColumnCount();

            Product.Builder productBuilder = product != null ?
                    product.Builder() :
                    Product.newBuilder()
                            .id(id)
                            .brand(EBrand.valueOf(rs.getString("brand")))
                            .nameModel(rs.getString("name_model"))
                            .price(rs.getDouble("price"));

            productBuilder
                    .image(
                            new Image(rs.getLong("image_id"),
                                    rs.getBytes("photo")));

            try{
                productBuilder.rating(new Rating(rs.getLong("rating_id"),
                            rs.getString("description"),
                            rs.getShort("grade")));
                productBuilder.characteristics(
                            new Characteristics(rs.getLong("characteristics_id"),
                                    rs.getString("name"),
                                    rs.getString("value"))
                );
            }catch (SQLException ignored){};

            productMap.put(id, productBuilder.build());
        }

        return new ArrayList<>(productMap.values());
    }
}

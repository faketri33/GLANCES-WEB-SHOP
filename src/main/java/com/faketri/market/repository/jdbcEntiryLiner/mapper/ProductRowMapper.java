package com.faketri.market.repository.jdbcEntiryLiner.mapper;

import com.faketri.market.entity.Characteristics;
import com.faketri.market.entity.Image;
import com.faketri.market.entity.Product;
import com.faketri.market.entity.Rating;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product.Builder productBuilder = Product
                .newBuilder()
                .id(rs.getLong("id"))
                .brand(EBrand.valueOf(rs.getString("brand")))
                .nameModel(rs.getString("name_model"))
                .price(rs.getDouble("price"))
                .image(
                        new Image(rs.getLong("image_id"),
                                rs.getBytes("photo"))
                );


        while (rs.next()) {
            productBuilder.image(
                    new Image(rs.getLong("image_id"),
                            rs.getBytes("photo")));
            try{
                productBuilder.rating(new Rating(
                        rs.getLong("rating_id"),
                        rs.getString("description"),
                        rs.getShort("grade"))
                );

                productBuilder.characteristics(new Characteristics(
                        rs.getLong("characteristic_id"),
                        rs.getString("name"),
                        rs.getString("value"))
                );
            }catch (SQLException ignored){}
        }
        return productBuilder.build();
    }
}

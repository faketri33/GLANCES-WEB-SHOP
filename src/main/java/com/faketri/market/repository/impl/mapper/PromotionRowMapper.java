package com.faketri.market.repository.impl.mapper;

import com.faketri.market.domain.promo.Promotion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;

public class PromotionRowMapper implements RowMapper<Promotion> {

    @Override
    public Promotion mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Promotion(
                rs.getLong("promotion_id"),
                rs.getBytes("banner"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getTimestamp("date_of_start").toLocalDateTime(),
                rs.getTimestamp("date_of_end").toLocalDateTime(),
                new HashSet<>(Objects.requireNonNull(new ProductExtractor().extractData(
                        rs)))
        );
    }

}

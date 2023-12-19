package com.faketri.market.repository.impl.mapper;

import com.faketri.market.entity.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PromotionRowMapper implements RowMapper<Promotion> {
    @Override
    public Promotion mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Promotion(rs.getLong("id"),
                rs.getBytes("banner"),
                rs.getString("title"),
                rs.getString("discription"),
                rs.getTimestamp("date_of_start").toLocalDateTime(),
                rs.getTimestamp("date_of_end").toLocalDateTime());
    }
}

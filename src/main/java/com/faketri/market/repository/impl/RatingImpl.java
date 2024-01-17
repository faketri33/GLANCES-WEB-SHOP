package com.faketri.market.repository.impl;

import com.faketri.market.domain.order.Rating;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class RatingImpl implements Repository<Long, Rating> {
    @Autowired
    private NamedParameterJdbcTemplate template;
    @Override
    public Optional<Rating> findById(Long id) {
        return Optional.ofNullable(template.queryForObject("select * from rating where id = :id", Map.of("id", id), Rating.class));
    }

    @Override
    public Rating findByFields(Rating entity) {
        try{
            return null;
        }
        catch (EmptyResultDataAccessException ex){
            throw new ResourceNotFoundException(this.getClass().getName() + " not found entity");
        }
    }

    public List<Rating> findByIdProduct(Long id) {
        return template.queryForList("select * from rating where product_id = :id", Map.of("id", id), Rating.class);
    }

    @Override
    public List<Rating> findAll() {
        return null;
    }

    @Override
    public Page<Rating> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Rating save(Rating entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update("insert into rating(product_id, description, user_id, grade) " +
                "values(:product_id, :description, :user_id, :grate)",
                new MapSqlParameterSource(Map.of("product_id", entity.getProduct().getId(),
                        "description", entity.getDescription(),
                        "user_id", entity.getUser().getId(),
                        "grate", entity.getGrade())), keyHolder, new String[] {"id"});
        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return entity;
    }

    @Override
    public Boolean update(Rating entity) {
        return null;
    }

    @Override
    public Boolean delete(Rating entity) {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}

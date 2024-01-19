package com.faketri.market.repository.impl;

import com.faketri.market.domain.product.Categories;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class CategoriesImpl implements Repository<Long, Categories> {
    @Autowired
    private NamedParameterJdbcTemplate template;
    @Override
    public Optional<Categories> findById(Long id) {
        return Optional.ofNullable(template.queryForObject("select id, name, image from categories",
                Map.of(), Categories.class));
    }

    @Override
    public Categories findByFields(Categories entity) {
        try {
            return template.queryForObject("select id, name, image from categories c where c.name = :name",
                    Map.of("name", entity.getName()),
                    (rs, numRows) -> new Categories(rs.getLong("id"), rs.getString("name"), rs.getBytes("image")));
        }
        catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Categories> findAll() {
        return template.query("select id, name, image from categories", new BeanPropertyRowMapper<>(Categories.class));
    }
    @Override
    public Page<Categories> findAll(Pageable pageable) {
        return new PageImpl<>(
                template.query("select id, name, image from categories", Map.of(), new BeanPropertyRowMapper<>(Categories.class)),
                pageable,
                countAll()
        );
    }
    @Override
    public Categories save(Categories entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update("insert into categories(name, image) values(:name, :image)",
                new MapSqlParameterSource(Map.of("name", entity.getName(),
                        "image", entity.getImage())), keyHolder, new String[] {"id"});
        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return entity;
    }
    @Override
    public Boolean update(Categories entity) {
        return template.update("update categories set name = :name, image =:image where id = :id",
                Map.of("id", entity.getId(), "name", entity.getName(), "image", entity.getImage())) > 0;
    }
    @Override
    public Boolean delete(Categories entity) {
        return template.update("delete from categories where id = :id",
                Map.of("id", entity.getId())) > 0;
    }


    @Override
    public int countAll() {
        return template.query("select count(*) from categories", (rs, numRow) ->
             rs.getInt(1)
        ).get(0);
    }
}

package com.faketri.market.repository.impl;

import com.faketri.market.domain.product.Categories;
import com.faketri.market.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return Optional.empty();
    }
    @Override
    public List<Categories> findAll() {
        return template.query("select categories.id, categories.name from categories",
                new BeanPropertyRowMapper<Categories>(Categories.class));
    }
    @Override
    public Page<Categories> findAll(Pageable pageable) {
        return new PageImpl<>(
                template.queryForList("select * from categories", Map.of(), Categories.class),
                pageable,
                countAll()
        );
    }
    @Override
    public Long save(Categories entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update("insert into categories(name) values(:name)",
                new MapSqlParameterSource(Map.of("name", entity.getName())), keyHolder, new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
    @Override
    public Boolean update(Categories entity) {
        return template.update("update categories set name = :name where id = :id",
                Map.of("id", entity.getId(), "name", entity.getName())) > 0;
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

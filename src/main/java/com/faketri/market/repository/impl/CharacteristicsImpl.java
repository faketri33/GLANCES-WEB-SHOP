package com.faketri.market.repository.impl;

import com.faketri.market.domain.product.Characteristics;
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
public class CharacteristicsImpl implements Repository<Long, Characteristics> {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Optional<Characteristics> findById(Long id) {
        return Optional.ofNullable(
                template.queryForObject("select * from characteristics where id = :id",
                        Map.of("id", id), (rs, numRow) ->
                                new Characteristics(rs.getLong("id"),
                                        rs.getString("name"),
                                        rs.getString("value"))
                )
        );
    }

    @Override
    public Characteristics findByFields(Characteristics entity) {
        try {
            return template.queryForObject("select * from characteristics c where c.name = :name and c.value = :value",
                    Map.of("name", entity.getName(), "value", entity.getValue()), (rs, numRow) ->
                            new Characteristics(rs.getLong("id"),
                                    rs.getString("name"),
                                    rs.getString("value"))
            );
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Characteristics> findAll() {
        return template.query("select * from characteristics", Map.of(), new BeanPropertyRowMapper<>(Characteristics.class));
    }

    @Override
    public Page<Characteristics> findAll(Pageable pageable) {
        return new PageImpl<>(
            template.query("select * from characteristics", Map.of(), new BeanPropertyRowMapper<>(Characteristics.class)), pageable, countAll()
        );
    }

    @Override
    public Characteristics save(Characteristics entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
         template.update("insert into characteristics(name, value) values(:name, :value)",
                new MapSqlParameterSource(Map.of("name", entity.getName(), "value", entity.getValue())),
                        keyHolder, new String[] {"id"});
         entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return entity;
    }

    @Override
    public Boolean update(Characteristics entity) {
        return template.update("update characteristics set name = :name, value = :value where id = :id",
                Map.of("name", entity.getName(), "value", entity.getValue(), "id", entity.getId())
                ) > 0;
    }

    @Override
    public Boolean delete(Characteristics entity) {
        return template.update("delete from characteristics where id = :id", Map.of("id", entity.getId())) > 0;
    }


    @Override
    public int countAll() {
        return template.query("select count(*) from characteristics", (rs, numRow) -> rs.getInt(1)).get(0);
    }
}

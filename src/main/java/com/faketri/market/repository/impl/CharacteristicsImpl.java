package com.faketri.market.repository.impl;

import com.faketri.market.domain.product.Characteristics;
import com.faketri.market.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
public class CharacteristicsImpl implements Repository<Long, Characteristics> {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Optional<Characteristics> findById(Long id) {
        return Optional.ofNullable(
                template.queryForObject("select * from characteristics where id = :id",
                        Map.of("id", id), Characteristics.class)
        );
    }

    @Override
    public List<Characteristics> findAll() {
        return template.queryForList("select * from characteristics", Map.of(), Characteristics.class);
    }

    @Override
    public Page<Characteristics> findAll(Pageable pageable) {
        return new PageImpl<>(
            template.queryForList("select * from characteristics", Map.of(), Characteristics.class), pageable, countAll()
        );
    }

    @Override
    public Long save(Characteristics entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
         template.update("insert into characteristics(name, value) values(:name, :value)",
                new MapSqlParameterSource(Map.of("name", entity.getName(), "value", entity.getValue())),
                        keyHolder, new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
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

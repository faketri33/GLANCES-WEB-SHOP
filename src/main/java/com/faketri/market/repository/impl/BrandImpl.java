package com.faketri.market.repository.impl;

import com.faketri.market.domain.product.Brand;
import com.faketri.market.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BrandImpl implements BrandRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Optional<Brand> findById(Long id) {
        return Optional.ofNullable(
                template.queryForObject("select * from brand where id = :id",
                Map.of("id", id),
                    (rs, numRow) -> new Brand(rs.getLong(1), rs.getString(2))
            ));
    }
    @Override
    public List<Brand> findAll() {
        return template.queryForList("select * from brand", Map.of(), Brand.class);
    }
    @Override
    public Page<Brand> findAll(Pageable pageable) {
        return new PageImpl<>(template.queryForList("select * from brand",
                Map.of(),
                Brand.class), pageable, countAll());
    }
    @Override
    public Long save(Brand entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update("insert into brand(name) values(:name)",
            new MapSqlParameterSource(Map.of("name", entity.getName())), keyHolder, new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
    @Override
    public Boolean update(Brand entity) {
        return template.update("update brand set brand.name = :name where brand.id = :id",
                Map.of("id", entity.getId(),
                        "name", entity.getName())) > 0;
    }
    @Override
    public Boolean delete(Brand entity) {
        return template.update("delete from brand where brand.id = :id", Map.of("id", entity.getId())) > 0;
    }
    @Override
    public int countAll() {
        return template.query("select count(*) from brand", Map.of(), (rs, numRow) -> rs.getInt(1)).get(0);
    }
}

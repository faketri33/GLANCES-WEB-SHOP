package com.faketri.market.repository.impl;

import com.faketri.market.entity.Brand;
import com.faketri.market.entity.Image;
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
public class ImageImpl implements Repository<Long, Image> {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Optional<Image> findById(Long id) {
        return Optional.ofNullable(
                template.queryForObject("select * from image where id = :id",
                Map.of("id", id),
                Image.class)
        );
    }

    @Override
    public List<Image> findAll() {
        return template.queryForList("select * from image", Map.of(), Image.class);
    }

    @Override
    public Page<Image> findAll(Pageable pageable) {
        return new PageImpl<>(template.queryForList("select * from image", Map.of(), Image.class), pageable, countAll());
    }

    @Override
    public Long save(Image entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update("insert into image(image) values(:photo)",
                new MapSqlParameterSource(Map.of("photo", entity.getPhoto())), keyHolder, new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Boolean update(Image entity) {
        return template.update("update image set photo = :photo where id = :id",
                Map.of("photo", entity.getPhoto(),
                        "id", entity.getId())) > 0;
    }

    @Override
    public Boolean delete(Image entity) {
        return template.update("delete from image where id = :id",
                Map.of("id", entity.getId())) > 0;
    }

    @Override
    public int countAll() {
        return template.query("select count(*) from image", (rs, numRows) -> rs.getInt(1)).get(0);
    }
}

package com.faketri.market.repository.impl;

import com.faketri.market.domain.image.Image;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.Repository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

@Log4j2
@org.springframework.stereotype.Repository
public class ImageImpl implements Repository<Long, Image> {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Optional<Image> findById(Long id) {
        try {
            return Optional.ofNullable(template.queryForObject(
                    "select * from image where id = :id",
                    Map.of("id", id),
                    (rs, numRow) -> new Image(rs.getLong("id"),
                                              rs.getBytes("image")
                    )
            ));
        } catch (DataAccessException e) {
            log.error("Image with id " + id + " not found");
            throw new ResourceNotFoundException("Image with id " + id + " not found");
        }
    }

    @Override
    public Image findByFields(Image entity) {
        try {
            return template.queryForObject(
                    "select id, image from image i where i.image = :image",
                    Map.of("image", entity.getPhoto()),
                    (rs, numRow) -> new Image(rs.getLong("id"),
                                              rs.getBytes("image")
                    )
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Image> findAll() {
        return template.query("select * from image",
                              Map.of(),
                              new BeanPropertyRowMapper<>(Image.class)
        );
    }

    @Override
    public Page<Image> findAll(Pageable pageable) {
        return new PageImpl<>(template.query("select * from image",
                                             Map.of(),
                                             new BeanPropertyRowMapper<>(Image.class)
        ), pageable, countAll());
    }

    @Override
    public Image save(Image entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update("insert into image(image) values(:image)",
                        new MapSqlParameterSource(Map.of("image",
                                                         entity.getPhoto()
                        )),
                        keyHolder,
                        new String[]{ "id" }
        );
        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return entity;
    }

    @Override
    public Boolean update(Image entity) {
        return template.update("update image set photo = :photo where id = :id",
                               Map.of("photo",
                                      entity.getPhoto(),
                                      "id",
                                      entity.getId()
                               )
        ) > 0;
    }

    @Override
    public Boolean delete(Image entity) {
        return template.update("delete from image where id = :id",
                               Map.of("id", entity.getId())
        ) > 0;
    }

    @Override
    public int countAll() {
        return template.query("select count(*) from image",
                              (rs, numRows) -> rs.getInt(1)
                       )
                       .get(0);
    }

}

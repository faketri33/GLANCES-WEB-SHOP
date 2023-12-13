package com.faketri.market.repository.impl;

import com.faketri.market.entity.User;
import com.faketri.market.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class UserImpl implements Repository<Long, User> {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Optional<User> findById(Long id){
        return Optional.ofNullable(template.queryForObject("select * from user where id = :id",
                Map.of("id", id),
                User.class));
    }
    public Optional<User> findByLogin(String Login){
        return Optional.ofNullable(template.queryForObject("select * from user where login = :login",
                Map.of("login", Login),
                User.class));
    }
    @Override
    public List<User> findAll() {
        return template.queryForList("select * from user", Map.of(), User.class);
    }
    @Override
    public Page<User> findAll(Pageable pageable) {
        return new PageImpl<>(
                template.queryForList("select * from user " +
                    "LIMIT " + pageable.getPageSize() + " " +
                    "OFFSET " + pageable.getOffset(),
                    Map.of(),
                    User.class
                ),
                pageable,
                countAll()
        );
    }

    @Override
    public Long save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
                "insert into user(email, login, password) values (:email, :login, :password)",
                new MapSqlParameterSource(Map.of(
                        "brand_id", user.getEmail(),
                        "name_model", user.getLogin(),
                        "price", user.getPassword()
                )
        ), keyHolder, new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Boolean update(User entity) {
        return null;
    }

    @Override
    public Boolean delete(User entity) {
        return null;
    }

    @Override
    public int countAll() {
        return template.query("select count(*) from user",
                (rs, rowNum) -> rs.getInt(1)).get(0);
    }
}

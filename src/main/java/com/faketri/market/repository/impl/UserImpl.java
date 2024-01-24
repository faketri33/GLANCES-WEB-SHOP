package com.faketri.market.repository.impl;

import com.faketri.market.domain.users.User;
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
public class UserImpl implements Repository<Long, User> {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Optional<User> findById(Long id){
        return Optional.ofNullable(template.queryForObject("select * from \"user\" where id = :id",
                Map.of("id", id),
                User.class));
    }

    @Override
    public User findByFields(User entity) {
        try{
            return null;
        }
        catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public Optional<User> findByLogin(String Login){
        return Optional.ofNullable(template.queryForObject(
                "select id, email, login, password from \"user\" where login = :login",
                Map.of("login", Login),
                new BeanPropertyRowMapper<>(User.class)));
    }
    @Override
    public List<User> findAll() {
        return template.queryForList("select id, email, login, password from \"user\"", Map.of(), User.class);
    }
    @Override
    public Page<User> findAll(Pageable pageable) {
        return new PageImpl<>(
                template.queryForList("select id, email, login, password from user " +
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
    public User save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
                "insert into \"user\"(email, login, password) values(:email, :login, :password)",
                new MapSqlParameterSource(Map.of(
                        "email", user.getEmail(),
                        "login", user.getLogin(),
                        "password", user.getPassword()
                )
        ), keyHolder, new String[] {"id"});
        user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return user;
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
        return template.query("select count(*) from \"user\"",
                (rs, rowNum) -> rs.getInt(1)).get(0);
    }
}

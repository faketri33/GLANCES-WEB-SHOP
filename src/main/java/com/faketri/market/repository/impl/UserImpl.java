package com.faketri.market.repository.impl;

import com.faketri.market.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserImpl implements CrudRepository<User, Long> {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public List<User> findAll() {
        return null;
    }


    @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
        return null;
    }

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
    public boolean existsById(Long aLong) {
        return false;
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }



    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }

}

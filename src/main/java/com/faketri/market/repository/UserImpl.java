package com.faketri.market.repository;

import com.faketri.market.entity.User;
import com.faketri.market.repository.Contract.RepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserImpl implements RepositoryDao<User> {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public Optional<User> findById(Long id){
        return Optional.ofNullable(template.queryForObject("select * from user where id = :id",
                Map.of("id", id),
                User.class));
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Page<User> findAllByPage(Pageable pageable) {
        return null;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    public Optional<User> findByLogin(String Login){
        return Optional.ofNullable(template.queryForObject("select * from user where login = :login",
                Map.of("login", Login),
                User.class));
    }
}

package com.faketri.market.repository.impl;

import com.faketri.market.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderImpl implements CrudRepository<Order, Long> {

    @Autowired
    private NamedParameterJdbcTemplate template;


    @Override
    public <S extends Order> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Order> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    public Optional<Order> findById(Long id){
        return Optional.ofNullable(template.queryForObject("select * from order where id = :id",
                Map.of("id", id),
                Order.class));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }


    public List<Order> findAll() {
        return null;
    }

    @Override
    public Iterable<Order> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Order entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Order> entities) {

    }

    @Override
    public void deleteAll() {

    }
}

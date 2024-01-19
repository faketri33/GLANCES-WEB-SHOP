package com.faketri.market.repository.impl;

import com.faketri.market.domain.order.Order;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderImpl implements com.faketri.market.repository.Repository<Long, Order> {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Order findByFields(Order entity) {
        try{
            return null;
        }
        catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Order save(Order entity) {
        return null;
    }

    @Override
    public Boolean update(Order entity) {
        return null;
    }

    @Override
    public Boolean delete(Order entity) {
        return null;
    }



    @Override
    public int countAll() {
        return 0;
    }
}

package com.faketri.market.repository;

import com.faketri.market.entity.Order;
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
public class OrderImpl implements RepositoryDao<Order> {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public Optional<Order> findById(Long id){
        return Optional.ofNullable(template.queryForObject("select * from order where id = :id",
                Map.of("id", id),
                Order.class));
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Page<Order> findByAllByPage(Pageable pageable) {
        return null;
    }

    public void save(Order order){

    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(Order entity) {

    }

}

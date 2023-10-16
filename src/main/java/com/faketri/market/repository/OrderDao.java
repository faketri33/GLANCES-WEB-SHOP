package com.faketri.market.repository;

import com.faketri.market.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderDao{

    @Autowired
    private NamedParameterJdbcTemplate template;

    public Optional<Order> findById(Long id){
        return Optional.ofNullable(template.queryForObject("select * from order where id = :id",
                Map.of("id", id),
                Order.class));
    }
    public void save(Order order){

    }

}

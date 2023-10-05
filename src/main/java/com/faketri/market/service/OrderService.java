package com.faketri.market.service;

import com.faketri.market.entity.Order;
import com.faketri.market.exception.responseEntity.ResourceNotFoundException;
import com.faketri.market.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public Order findById(Long id){
        return orderDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order with id " + id + " not found")
        );
    }
    public List<Order> findByUserId(Long userId){
        return orderDao.findByUserId(userId);
    }

    public void save(Order order){
        orderDao.save(order);
    }
}

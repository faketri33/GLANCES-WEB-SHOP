package com.faketri.market.usecase.userPayload.order;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.userPayload.order.gateway.OrderRepository;
import com.faketri.market.entity.userPayload.order.model.Orders;
import com.faketri.market.infastructure.userPayload.order.gateway.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrdersServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrdersServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Orders findById(UUID uuid) {
        return orderRepository.findById(uuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Заказ с номером " + uuid + " не найден."));
    }

    @Override
    public Page<Orders> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Orders> findByUser(UUID uuidUser, Pageable pageable) {
        return orderRepository.findByUsers_Id(uuidUser, pageable);
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }
}

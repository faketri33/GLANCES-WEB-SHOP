package com.faketri.market.usecase.user.payload.order;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.user.payload.order.gateway.OrderRepository;
import com.faketri.market.entity.user.payload.order.model.EStatusOrder;
import com.faketri.market.entity.user.payload.order.model.Orders;
import com.faketri.market.infastructure.user.payload.order.gateway.OrderService;
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
    public Page<Orders> findByUuidSuffixAndStatusOrder(String uuidSuffix, Pageable pageable, EStatusOrder statusOrder) {
        return orderRepository.findByUuidSuffixAndStatusOrder(uuidSuffix, uuidSuffix.length(), pageable, statusOrder);
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

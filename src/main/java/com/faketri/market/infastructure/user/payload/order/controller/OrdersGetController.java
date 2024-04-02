package com.faketri.market.infastructure.user.payload.order.controller;

import com.faketri.market.entity.user.payload.order.model.Orders;
import com.faketri.market.infastructure.user.payload.order.gateway.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Orders", description = "Operation with orders")
public class OrdersGetController {

    private final OrderService orderService;

    public OrdersGetController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/")
    public Page<Orders> findAll() {
        return orderService.findAll(PageRequest.of(0, 10));
    }
}

package com.faketri.market.infastructure.user.payload.order.controller;

import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.entity.user.payload.order.gateway.mapper.OrderMapper;
import com.faketri.market.entity.user.payload.order.model.EStatusOrder;
import com.faketri.market.entity.user.payload.order.model.Orders;
import com.faketri.market.infastructure.user.payload.order.dto.ChangeStatusRequest;
import com.faketri.market.infastructure.user.payload.order.dto.OrdersDto;
import com.faketri.market.infastructure.user.payload.order.gateway.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/orders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Orders", description = "Operation with orders")
public class OrdersPostController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final OrderService orderService;

    public OrdersPostController(OrderService orderService) {
        this.orderService = orderService;
    }

    public Orders save(@RequestBody Orders orders) {
        return orderService.save(orders);
    }

    @RequestMapping("/create")
    public OrdersDto createOrder(@RequestBody final List<ProductItem> product) {
        return orderService.create(product);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/{id}/change-status")
    public OrdersDto changeStatus(@PathVariable("id") UUID uuid,
                                  @RequestBody ChangeStatusRequest statusOrder) {
        log.info("changeStatus: order - " + uuid + " status - " + statusOrder.getStatus());
        Orders orders = orderService.findById(uuid);

        if (statusOrder.getStatus() == null) throw new RuntimeException("Статус заказа не может быть пустым");

        orders.setStatusOrder(EStatusOrder.valueOf(statusOrder.getStatus()));
        return OrderMapper.toDto(orderService.save(orders));
    }
}

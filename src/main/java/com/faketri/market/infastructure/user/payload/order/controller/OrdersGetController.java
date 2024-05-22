package com.faketri.market.infastructure.user.payload.order.controller;

import com.faketri.market.entity.user.payload.order.gateway.mapper.OrderMapper;
import com.faketri.market.entity.user.payload.order.model.EStatusOrder;
import com.faketri.market.infastructure.user.payload.order.dto.OrdersDto;
import com.faketri.market.infastructure.user.payload.order.gateway.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Orders", description = "Operation with orders")
public class OrdersGetController {

    private final OrderService orderService;

    public OrdersGetController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping("/id/{id}")
    public OrdersDto findAll(@PathVariable("id") UUID id) {
        System.out.println("HELLO");
        return OrderMapper.toDto(orderService.findById(id));
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping("/")
    public Page<OrdersDto> findAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "suffix", required = false) String suffix,
            @RequestParam(value = "status", required = false) EStatusOrder statusOrder
    ) {
        return suffix == null && statusOrder == null ?
                orderService.findAll(PageRequest.of(page, size)).map(OrderMapper::toDto)
                : orderService
                .findByUuidSuffixAndStatusOrder(suffix, PageRequest.of(page, size), statusOrder)
                .map(OrderMapper::toDto);
    }
}

package com.faketri.market.entity.user.payload.order.gateway.mapper;

import com.faketri.market.entity.user.payload.order.model.EStatusOrder;
import com.faketri.market.entity.user.payload.order.model.Orders;
import com.faketri.market.entity.user.payload.payment.model.Payment;
import com.faketri.market.entity.user.payload.payment.model.PaymentStatus;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.user.payload.order.dto.OrdersDto;
import com.faketri.market.infastructure.user.payload.user.dto.UserSmallDataResponse;

public class OrderMapper {

    public static OrdersDto toDto(Orders orders) {
        return new OrdersDto(
                orders.getId(),
                new UserSmallDataResponse(orders.getUsers().getId(),
                        orders.getUsers().getProfileImage(),
                        orders.getUsers().getLogin(),
                        orders.getUsers().getCity()),
                orders.getProducts(),
                orders.getDateOfCreate(),
                orders.getDateOfRelease(),
                orders.getPrice(),
                orders.getPayment(),
                orders.getStatusOrder()
        );
    }

    public static Orders toObj(OrdersDto ordersDto) {
        return new Orders(
                ordersDto.getId(),
                new Users(ordersDto.getUsers().getId(),
                        ordersDto.getUsers().getProfileImage(),
                        ordersDto.getUsers().getLogin(),
                        ordersDto.getUsers().getCity()),
                ordersDto.getProducts(),
                ordersDto.getDateOfCreate(),
                ordersDto.getDateOfRelease(),
                ordersDto.getPrice(),
                new Payment(
                        ordersDto.getPayment().getId(),
                        new Users(ordersDto.getUsers().getId(),
                                ordersDto.getUsers().getProfileImage(),
                                ordersDto.getUsers().getLogin(),
                                ordersDto.getUsers().getCity()
                        ),
                        PaymentStatus.valueOf(ordersDto.getPayment().getPaymentStatus()),
                        ordersDto.getPayment().getDateOfCreate(),
                        ordersDto.getPayment().getDateOfPayment()
                ),
                EStatusOrder.valueOf(ordersDto.getStatusOrder())
        );
    }
}

package com.faketri.market.infastructure.user.payload.order.gateway;

import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.entity.user.payload.order.model.EStatusOrder;
import com.faketri.market.entity.user.payload.order.model.Orders;
import com.faketri.market.infastructure.user.payload.order.dto.OrdersDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Orders findById(UUID uuid);

    Page<Orders> findAll(Pageable pageable);

    Page<Orders> findByUuidSuffixAndStatusOrder(String uuidSuffix, Pageable pageable, EStatusOrder statusOrder);

    Page<Orders> findByUser(UUID uuidUser, Pageable pageable);

    OrdersDto create(final List<ProductItem> product);

    Orders save(Orders orders);
}

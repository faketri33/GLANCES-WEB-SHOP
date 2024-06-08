package com.faketri.market.infastructure.user.payload.order.gateway;

import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.entity.user.payload.order.model.EStatusOrder;
import com.faketri.market.entity.user.payload.order.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Orders findById(UUID uuid);

    Page<Orders> findAll(Pageable pageable);

    Page<Orders> findByUuidSuffix(String uuidSuffix, Pageable pageable);

    Page<Orders> findByUser(UUID uuidUser, Pageable pageable);

    Orders create(final List<ProductItem> product);

    Orders changeStatus(final UUID id, final EStatusOrder eStatusOrder);

    Orders save(Orders orders);
}

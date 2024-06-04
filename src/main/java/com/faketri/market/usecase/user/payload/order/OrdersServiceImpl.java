package com.faketri.market.usecase.user.payload.order;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.entity.user.payload.order.gateway.OrderRepository;
import com.faketri.market.entity.user.payload.order.gateway.mapper.OrderMapper;
import com.faketri.market.entity.user.payload.order.model.EStatusOrder;
import com.faketri.market.entity.user.payload.order.model.Orders;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import com.faketri.market.infastructure.user.payload.order.dto.OrdersDto;
import com.faketri.market.infastructure.user.payload.order.gateway.OrderService;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrderService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    public OrdersServiceImpl(OrderRepository orderRepository, UserService userService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
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
    public OrdersDto create(List<ProductItem> productsItems) {
        final Users user = userService.getCurrentUser();
        log.info("CREATE ORDER: user id - " + user.getId());

        productService.updateQuantityAndQuantitySold(productsItems);

        Orders orders = new Orders();

        orders.setUsers(user);
        orders.getProducts().addAll(
                productsItems.stream()
                        .map(p -> new ProductItem(null, p.getProduct(), p.getQuantity()))
                        .toList()
        );

        return OrderMapper.toDto(orderRepository.save(orders));
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }
}

package com.infonal.orderservice.domain.port;

import com.infonal.orderservice.domain.model.Order;
import com.infonal.orderservice.domain.model.OrderId;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(OrderId orderId);
    List<Order> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(OrderId orderId);
    long countAll();

}

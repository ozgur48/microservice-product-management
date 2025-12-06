package com.infonal.orderservice.domain.exception;

import com.infonal.orderservice.domain.model.OrderStatus;

public class OrderCanNotCancel extends IllegalStateException {
    public OrderCanNotCancel(OrderStatus status) {
        super(String.format("Order cannot be cancelled in status {%s}", status));
    }
}

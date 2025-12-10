package com.infonal.orderservice.domain.exception;

import java.util.UUID;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(UUID id) {
        super(String.format("Order with ID: {%s} can not find", id));
    }
}

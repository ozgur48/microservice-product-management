package com.infonal.orderservice.domain.model;

import java.util.Objects;
import java.util.UUID;

public record OrderId(UUID id) {
    public OrderId{
        Objects.requireNonNull(id, "Order ID can not be null");
    }
    public static OrderId generate(){
        return new OrderId(UUID.randomUUID());
    }
}

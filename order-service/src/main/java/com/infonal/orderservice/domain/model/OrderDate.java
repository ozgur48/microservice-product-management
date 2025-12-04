package com.infonal.orderservice.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record OrderDate(LocalDateTime value) {
    public OrderDate{
        Objects.requireNonNull(value, "Order date can not be null");
    }
}
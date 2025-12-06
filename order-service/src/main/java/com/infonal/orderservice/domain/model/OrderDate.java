package com.infonal.orderservice.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record OrderDate(LocalDateTime value) {
    public OrderDate{
        Objects.requireNonNull(value, "Order date can not be null");
    }
    public static OrderDate of(LocalDateTime value){
        return new OrderDate(value);
    }
    public static OrderDate now(){
        return new OrderDate(LocalDateTime.now());
    }
}
package com.infonal.orderservice.domain.model;

import java.util.Objects;
import java.util.UUID;

public record ProductId(UUID id) {
    public ProductId{
        Objects.requireNonNull(id,"Product ID can not be null");
    }
    public static ProductId of(UUID id){
        return new ProductId(id);
    }
}

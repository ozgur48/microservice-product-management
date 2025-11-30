package com.infonal.productservice.domain.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record ProductId(UUID value) implements Serializable {
    public ProductId{
        Objects.requireNonNull(value, "ProductId can not be null ");
    }
    public static ProductId of(UUID id){
        return new ProductId(id);
    }
    public static ProductId generate(){
        return new ProductId(UUID.randomUUID());
    }
}

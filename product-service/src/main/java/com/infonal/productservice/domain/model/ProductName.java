package com.infonal.productservice.domain.model;

import com.infonal.productservice.domain.exception.ProductNameInvalidLength;

import java.util.Objects;

public record ProductName(String value) {
    public ProductName {
        Objects.requireNonNull(value, "Product name can not be null");
        String trimmed = value.trim();
        if(trimmed.length() < 2 ){
            throw new ProductNameInvalidLength(value);
        }

        if(trimmed.length() > 100){
            throw new ProductNameInvalidLength(value);
        }
    }
    public static ProductName of(String value){
        return new ProductName(value);
    }

}

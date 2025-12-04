package com.infonal.orderservice.domain.model;

import com.infonal.orderservice.domain.exception.QuantityMustBePositive;

public record Quantity(int value) {
    public Quantity{
        if(value <= 0){
            throw new QuantityMustBePositive(value);
        }
    }
}

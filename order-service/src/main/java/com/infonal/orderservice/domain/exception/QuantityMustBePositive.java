package com.infonal.orderservice.domain.exception;

public class QuantityMustBePositive extends RuntimeException {
    public QuantityMustBePositive(int quantity) {
        super(String.format("Quantity {%d} must be positive", quantity));
    }
}

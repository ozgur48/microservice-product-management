package com.infonal.productservice.domain.exception;

public class ProductStockInsufficient extends RuntimeException {
    public ProductStockInsufficient(int stock) {
        super(String.format("Quantity {%d} must be greater than zero for selling ", stock));
    }
}

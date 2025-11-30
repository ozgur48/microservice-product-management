package com.infonal.productservice.domain.exception;

public class StockMustBePositive extends IllegalArgumentException{
    public StockMustBePositive(int currentStock) {
        super(String.format(
                "Stock '%d' can not be negative, must be positive",
                currentStock
        ));
    }
}

package com.infonal.productservice.domain.exception;

public class StockCanNotBeNegative extends IllegalArgumentException{
    public StockCanNotBeNegative(int currentStock) {
        super(String.format(
                "Stock '%d' can not be negative, must be positive",
                currentStock
        ));
    }
}

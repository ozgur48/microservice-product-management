package com.infonal.orderservice.domain.exception;

import java.math.BigDecimal;

public class AmountMustBePositive extends IllegalArgumentException {
    public AmountMustBePositive(BigDecimal amount) {
        super(String.format("Amount {%s} must be positive", amount));
    }
}

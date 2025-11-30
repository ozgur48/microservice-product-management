package com.infonal.productservice.domain.exception;

import java.math.BigDecimal;

public class AmountMustBePositive extends IllegalArgumentException {
    public AmountMustBePositive(BigDecimal currentAmount) {
        super("Amount '" + currentAmount + "' can not be negative");
    }
}

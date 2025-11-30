package com.infonal.productservice.domain.model;

import com.infonal.productservice.domain.exception.AmountMustBePositive;

import java.math.BigDecimal;
import java.util.Objects;

public record Money(BigDecimal amount, String currency) {
    public Money {
        Objects.requireNonNull(amount, "Amount can not be null");
        Objects.requireNonNull(currency, "Currency can not be null");

        if(amount.compareTo(BigDecimal.ZERO) < 0 ){
            throw new AmountMustBePositive(amount);
        }
    }
}

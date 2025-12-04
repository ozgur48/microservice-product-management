package com.infonal.orderservice.domain.model;

import com.infonal.orderservice.domain.exception.AmountMustBePositive;

import java.math.BigDecimal;
import java.util.Objects;

public record Money(BigDecimal amount, String currency) {
    public Money{
        Objects.requireNonNull(amount,"amount can not be null");
        Objects.requireNonNull(currency, "currency can not be null");
        if(amount.compareTo(BigDecimal.ZERO) < 0 ){
            throw new AmountMustBePositive(amount);
        }
    }
    public static Money of(BigDecimal amount, String currency){
        return new Money(amount, currency);
    }
}

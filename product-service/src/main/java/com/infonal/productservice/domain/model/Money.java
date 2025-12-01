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
    public static Money of(BigDecimal amount, String currency){
       return new Money(amount, currency);
    }
    public Money change(BigDecimal newAmount){
        return new Money(newAmount, this.currency);
    }
    public Money subtract(Money value){
        if(!this.currency.equals(value.currency)) {
            throw new IllegalArgumentException("Currencies must match to subtract");
        }
        BigDecimal result = this.amount.subtract(value.amount);

        if(result.compareTo(BigDecimal.ZERO) < 0 ){
            throw new AmountMustBePositive(result);
        }
        return new Money(result, this.currency);
    }

}

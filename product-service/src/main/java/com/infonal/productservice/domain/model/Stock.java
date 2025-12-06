package com.infonal.productservice.domain.model;

import com.infonal.productservice.domain.exception.StockMustBePositive;


public record Stock(int value) {
    public Stock{
        if(value < 0){
            throw new StockMustBePositive(value);
        }
    }
    public static Stock of(int stock){
        return new Stock(stock);
    }
    public boolean canReserve(int value){
        return this.value >= value;
    }
    public Stock increase(int amount){
        if(amount < 0 ){
            throw new IllegalArgumentException("Increase amount can not be negative");
        }
        return new Stock(this.value + amount);
    }
    public Stock decrease(int amount){
        if(amount < 0 ){
            throw new IllegalArgumentException("Decrease amount can not be negative");
        }
        int result = this.value - amount;
        if(result < 0 ){
            throw new IllegalArgumentException("Stock can not go below zero");
        }
        return new Stock(result);
    }

}

package com.infonal.productservice.domain.model;

import java.util.Objects;

public record Description(String value) {
    public Description{
        Objects.requireNonNull(value,"Description can not be null");
        if(value.length() < 10 ){
            throw new IllegalArgumentException("Description must be minimum 10 characters");
        }
    }
    public static Description of(String value){
        return new Description(value);
    }
}

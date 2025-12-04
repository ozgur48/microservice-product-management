package com.infonal.orderservice.domain.model;

import com.infonal.orderservice.domain.exception.CityMustBeMinimumTwoCharacters;

import java.util.Objects;

public record Address(String city,
                      String district) {
    public Address{
        Objects.requireNonNull(city,"City can not be null");
        Objects.requireNonNull(district,"District can not be null");
        if(city.length() < 2 ){
            throw new CityMustBeMinimumTwoCharacters(city);
        }
        if(district.length() < 2 ){
            throw new IllegalArgumentException("District must be minimum 2 characters");
        }
    }

}

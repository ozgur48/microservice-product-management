package com.infonal.orderservice.domain.exception;

public class CityMustBeMinimumTwoCharacters extends IllegalArgumentException {
    public CityMustBeMinimumTwoCharacters(String city) {
        super(String.format("City {%s} must be minimum 2 characters", city));
    }
}
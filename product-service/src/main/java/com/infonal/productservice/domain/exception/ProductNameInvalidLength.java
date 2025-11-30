package com.infonal.productservice.domain.exception;

public class ProductNameInvalidLength extends IllegalArgumentException {
    public ProductNameInvalidLength(String name) {
        super(String.format(
                "Product name '%s' must be minimum 2 characters and maximum 100 characters",
                name
        ));
    }
}

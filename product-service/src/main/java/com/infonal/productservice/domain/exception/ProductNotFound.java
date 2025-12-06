package com.infonal.productservice.domain.exception;

import java.util.UUID;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(UUID productId) {
        super(String.format("Product with ID: {%s} not found.", productId));
    }
}

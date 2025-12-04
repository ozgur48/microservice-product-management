package com.infonal.productservice.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatedProductResponse(
        UUID productId,
        String productName,
        String description,
        int stock,
        BigDecimal amount,
        String currency
) { }

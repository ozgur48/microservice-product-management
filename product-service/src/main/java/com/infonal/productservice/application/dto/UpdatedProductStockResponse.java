package com.infonal.productservice.application.dto;

import java.util.UUID;

public record UpdatedProductStockResponse(
        UUID productId,
        int updatedStock) { }

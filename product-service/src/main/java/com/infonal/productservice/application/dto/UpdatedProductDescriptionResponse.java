package com.infonal.productservice.application.dto;

import java.util.UUID;

public record UpdatedProductDescriptionResponse(
        UUID productId,
        String description) {
}

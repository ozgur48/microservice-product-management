package com.infonal.productservice.application.command;

import com.infonal.productservice.application.dto.CreatedProductResponse;
import com.infonal.productservice.core.cqrs.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateProductCommand(
        @NotNull String productName,
        @NotNull String description,
        @Positive int stock,
        @NotNull BigDecimal amount,
        @NotNull String currency
        ) implements Command<CreatedProductResponse> {
}

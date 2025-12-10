package com.infonal.productservice.application.command;

import com.infonal.productservice.application.dto.UpdatedProductStockResponse;
import com.infonal.productservice.core.cqrs.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record UpdateProductStockCommand(
        @NotNull UUID productId,
        @Positive int newStock) implements Command<UpdatedProductStockResponse> { }

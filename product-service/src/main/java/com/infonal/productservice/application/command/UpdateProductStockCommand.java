package com.infonal.productservice.application.command;

import com.infonal.productservice.application.dto.UpdatedProductStockResponse;
import com.infonal.productservice.core.cqrs.Command;

import java.util.UUID;

public record UpdateProductStockCommand(
        UUID productId,
        int newStock) implements Command<UpdatedProductStockResponse> { }

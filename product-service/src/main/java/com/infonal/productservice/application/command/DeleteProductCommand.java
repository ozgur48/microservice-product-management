package com.infonal.productservice.application.command;

import com.infonal.productservice.application.dto.DeletedProductResponse;
import com.infonal.productservice.core.cqrs.Command;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeleteProductCommand(@NotNull UUID id) implements Command<DeletedProductResponse> {
}

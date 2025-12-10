package com.infonal.productservice.application.command;

import com.infonal.productservice.application.dto.UpdatedProductDescriptionResponse;
import com.infonal.productservice.core.cqrs.Command;

import java.util.UUID;

public record UpdateProductDescriptionCommand(
        UUID productId,
        String newDescription) implements Command<UpdatedProductDescriptionResponse> {
}

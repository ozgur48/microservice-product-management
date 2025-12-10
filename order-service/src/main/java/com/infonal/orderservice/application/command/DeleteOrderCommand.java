package com.infonal.orderservice.application.command;

import com.infonal.orderservice.application.dto.DeletedOrderResponse;
import com.infonal.orderservice.cqrs.Command;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeleteOrderCommand(@NotNull UUID id) implements Command<DeletedOrderResponse> {
}

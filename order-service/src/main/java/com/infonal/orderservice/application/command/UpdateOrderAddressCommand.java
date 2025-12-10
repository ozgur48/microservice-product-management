package com.infonal.orderservice.application.command;

import com.infonal.orderservice.application.dto.UpdatedOrderAddressResponse;

import com.infonal.orderservice.cqrs.Command;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateOrderAddressCommand(
        @NotNull UUID orderId ,
        @NotNull String city,
        @NotNull String district) implements Command<UpdatedOrderAddressResponse> {
}

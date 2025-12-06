package com.infonal.orderservice.application.command;

import com.infonal.orderservice.application.dto.CreatedOrderResponse;
import com.infonal.orderservice.application.dto.OrderStatusDto;
import com.infonal.orderservice.cqrs.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateOrderCommand(
        @NotNull UUID orderId,
        @NotNull UUID productId,
        @Positive int quantity,
        @NotNull @Size(min = 3, max = 100) String city,
        @NotNull @Size(min = 3, max = 100) String district,
        @Positive BigDecimal amount,
        @NotNull @Length(min = 3, max = 3) String currency
        ) implements Command<CreatedOrderResponse> {
}

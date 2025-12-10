package com.infonal.orderservice.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderSummaryResponse(
        UUID orderId,
        LocalDateTime orderDate,
        BigDecimal amount,
        String currency,
        OrderStatusDto orderStatus
) {
}

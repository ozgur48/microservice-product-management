package com.infonal.orderservice.application.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreatedOrderResponse(
        UUID orderId,
        UUID productId,
        int quantity,
        String city,
        String district,
        LocalDateTime orderDate,
        BigDecimal amount,
        String currency,
        String orderStatus
) { }

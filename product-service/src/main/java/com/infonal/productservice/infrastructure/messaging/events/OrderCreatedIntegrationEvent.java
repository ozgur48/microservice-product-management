package com.infonal.productservice.infrastructure.messaging.events;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderCreatedIntegrationEvent(
        UUID eventId,
        LocalDateTime occurredOn,
        UUID orderId,
        UUID productId,
        int quantity,
        String city,
        String district,
        LocalDateTime orderDate,
        BigDecimal amount,
        String currency,
        String orderStatus
) {
    public OrderCreatedIntegrationEvent(UUID orderId,
                                        UUID productId,
                                        int quantity,
                                        String city,
                                        String district,
                                        LocalDateTime orderDate,
                                        BigDecimal amount,
                                        String currency,
                                        String orderStatus){
        this(UUID.randomUUID(), LocalDateTime.now(), orderId, productId, quantity,
                city, district, orderDate, amount, currency, orderStatus);
    }

}

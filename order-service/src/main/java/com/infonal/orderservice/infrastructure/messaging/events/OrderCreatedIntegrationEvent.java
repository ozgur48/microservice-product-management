package com.infonal.orderservice.infrastructure.messaging.events;

import com.infonal.orderservice.domain.event.DomainEvent;


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
)implements DomainEvent {
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

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public LocalDateTime getOccuredOn() {
        return occurredOn;
    }
}

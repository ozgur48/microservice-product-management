package com.infonal.orderservice.domain.event;

import com.infonal.orderservice.domain.model.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderCancelledEvent(UUID eventId,
                                  LocalDateTime occurredOn,
                                  OrderId orderId,
                                  ProductId productId,
                                  Quantity quantity,
                                  OrderStatus previousStatus,
                                  String reason) implements DomainEvent {
    public OrderCancelledEvent(OrderId orderId,
                               ProductId productId,
                               Quantity quantity,
                               OrderStatus previousStatus,
                               String reason){
        this(UUID.randomUUID(), LocalDateTime.now(), orderId, productId,
                quantity, previousStatus, reason);
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

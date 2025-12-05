package com.infonal.orderservice.domain.event;

import com.infonal.orderservice.domain.model.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderCreatedEvent (
        UUID eventId,
        LocalDateTime occurredOn,
        OrderId orderId,
        ProductId productId,
        Quantity quantity,
        Address address,
        OrderDate orderDate,
        Money money,
        OrderStatus orderStatus
) implements DomainEvent{
    public OrderCreatedEvent(OrderId orderId,
                             ProductId productId,
                             Quantity quantity,
                             Address address,
                             OrderDate orderDate,
                             Money money,
                             OrderStatus orderStatus){
        this(UUID.randomUUID(), LocalDateTime.now(),
                orderId, productId,
                quantity, address,
                orderDate, money, orderStatus);
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

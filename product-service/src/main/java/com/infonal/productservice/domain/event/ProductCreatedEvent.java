package com.infonal.productservice.domain.event;

import com.infonal.productservice.domain.model.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductCreatedEvent(
        UUID eventId,
        LocalDateTime occurredOn,
        ProductId productId,
        ProductName productName,
        Description description,
        Stock stock,
        Money money
) implements DomainEvent {

    public ProductCreatedEvent(ProductId productId,
                        ProductName productName,
                        Description description,
                        Stock stock,
                        Money money){
        this(UUID.randomUUID(),LocalDateTime.now(),
                productId, productName,
                description, stock, money);
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

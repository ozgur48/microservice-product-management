package com.infonal.productservice.infrastructure.messaging.events;

import com.infonal.productservice.domain.event.DomainEvent;
import com.infonal.productservice.domain.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductCreatedIntegrationEvent(
        UUID eventId,
        LocalDateTime occurredOn,
        UUID productId,
        String productName,
        String description,
        int stock,
        BigDecimal amount,
        String currency

) implements DomainEvent {
    public ProductCreatedIntegrationEvent(UUID productId,
                                          String productName,
                                          String description,
                                          int stock,
                                          BigDecimal amount,
                                          String currency){
        this(UUID.randomUUID(), LocalDateTime.now(),
                productId, productName, description,
                stock, amount, currency);
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

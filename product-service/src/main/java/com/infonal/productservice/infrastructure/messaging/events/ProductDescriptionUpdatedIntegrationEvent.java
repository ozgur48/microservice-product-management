package com.infonal.productservice.infrastructure.messaging.events;

import com.infonal.productservice.domain.event.DomainEvent;
import com.infonal.productservice.domain.model.Description;
import com.infonal.productservice.domain.model.ProductId;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductDescriptionUpdatedIntegrationEvent(UUID eventId,
                                             LocalDateTime occurredOn,
                                             ProductId productId,
                                             Description description) implements DomainEvent {

    public ProductDescriptionUpdatedIntegrationEvent(ProductId productId, Description description){
        this(UUID.randomUUID(), LocalDateTime.now(), productId, description);
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

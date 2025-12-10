package com.infonal.productservice.domain.event;

import com.infonal.productservice.domain.model.Description;
import com.infonal.productservice.domain.model.ProductId;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductDescriptionUpdatedEvent(UUID eventId,
                                             LocalDateTime occurredOn,
                                             ProductId productId,
                                             Description description) implements DomainEvent {

    public ProductDescriptionUpdatedEvent(ProductId productId, Description description){
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

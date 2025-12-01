package com.infonal.productservice.domain.event;

import com.infonal.productservice.domain.model.Money;
import com.infonal.productservice.domain.model.ProductId;
import com.infonal.productservice.domain.model.ProductName;

import java.time.LocalDateTime;
import java.util.UUID;

public record DiscountEvent(UUID eventId,
                            LocalDateTime occurredOn,
                            ProductId productId,
                            ProductName productName,
                            Money money) implements DomainEvent {

    public DiscountEvent(ProductId productId,
                         ProductName productName,
                         Money money){
        this(UUID.randomUUID(), LocalDateTime.now(), productId, productName, money);
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

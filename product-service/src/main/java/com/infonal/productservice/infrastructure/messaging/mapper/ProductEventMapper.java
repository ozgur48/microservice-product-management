package com.infonal.productservice.infrastructure.messaging.mapper;

import com.infonal.productservice.domain.event.ProductCreatedEvent;
import com.infonal.productservice.infrastructure.messaging.events.ProductCreatedIntegrationEvent;
import org.springframework.stereotype.Component;

@Component
public class ProductEventMapper {
    public ProductCreatedIntegrationEvent toIntegrationEvent(ProductCreatedEvent event){
        return new ProductCreatedIntegrationEvent(
                event.eventId(),
                event.occurredOn(),
                event.productId().value(),
                event.productName().value(),
                event.description().value(),
                event.stock().value(),
                event.money().amount(),
                event.money().currency()
        );
    }
}

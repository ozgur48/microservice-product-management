package com.infonal.orderservice.infrastructure.messaging.mapper;

import com.infonal.orderservice.domain.event.OrderCreatedEvent;
import com.infonal.orderservice.infrastructure.messaging.events.OrderCreatedIntegrationEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderEventMapper {
    public OrderCreatedIntegrationEvent toIntegrationEvent(OrderCreatedEvent event){
        return new OrderCreatedIntegrationEvent(
                event.eventId(),
                event.occurredOn(),
                event.orderId().id(),
                event.productId().id(),
                event.quantity().value(),
                event.address().city(),
                event.address().district(),
                event.orderDate().value(),
                event.money().amount(),
                event.money().currency(),
                event.orderStatus().toString()
        );
    }
}

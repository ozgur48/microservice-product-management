package com.infonal.productservice.infrastructure.messaging.consumer;

import com.infonal.productservice.application.eventHandlers.ProductOrderService;
import com.infonal.productservice.infrastructure.messaging.events.OrderCreatedIntegrationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class OrderCreatedEventConsumer {
    private final ProductOrderService productOrderService;

    public OrderCreatedEventConsumer(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }
    @Bean
    public Consumer<OrderCreatedIntegrationEvent> orderEvents(){
        return event ->{
            productOrderService.processOrderCreation(event);
        };
    }
}

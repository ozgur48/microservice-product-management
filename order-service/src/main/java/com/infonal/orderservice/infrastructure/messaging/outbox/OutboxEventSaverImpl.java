package com.infonal.orderservice.infrastructure.messaging.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infonal.orderservice.domain.event.DomainEvent;
import com.infonal.orderservice.domain.event.OrderCreatedEvent;
import com.infonal.orderservice.domain.model.AggregateRoot;
import com.infonal.orderservice.infrastructure.messaging.mapper.OrderEventMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxEventSaverImpl implements OutboxEventSaver{
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final OrderEventMapper orderEventMapper;

    public OutboxEventSaverImpl(OutboxRepository outboxRepository, ObjectMapper objectMapper, OrderEventMapper orderEventMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
        this.orderEventMapper = orderEventMapper;
    }

    @Override
    public void saveEvents(AggregateRoot aggregateRoot) {
        List<DomainEvent> events = aggregateRoot.getDomainEvents();
        for (DomainEvent event : events) {
            Object integrationEvent;
            String eventTypeForOutbox;
            if (event instanceof OrderCreatedEvent orderCreatedEvent) {
                integrationEvent = orderEventMapper.toIntegrationEvent(orderCreatedEvent);
                eventTypeForOutbox = orderCreatedEvent.getClass().getName();
            } else {
                throw new RuntimeException("Domain Event type not mapped for Outbox serialization: " + event.getClass().getName());
            }
            try {
                String payloadJson = objectMapper.writeValueAsString(integrationEvent);
                OutboxMessage message = new OutboxMessage(
                        event.getEventId(),
                        aggregateRoot.getClass().getSimpleName(),
                        aggregateRoot.getIdValue(),
                        event.getClass().getName(),
                        payloadJson
                );
                outboxRepository.save(message);
            } catch (JsonProcessingException e) {
                // Serileştirme hatası kritik bir iş hatasıdır ve Transaction'ı geri almalıdır
                // (rollback).
                // Bu nedenle Unchecked Exception (Runtime) fırlatılır.
                throw new RuntimeException("Error serializing Domain Event for Outbox: " + event.getEventId(), e);
            }
        }
        // Aggregate'i Temizleme
        // Olaylar artık kalıcı olarak Outbox tablosunda güvende olduğu için bellekteki
        // listeyi temizle.
        aggregateRoot.clearDomainEvents();
    }
}

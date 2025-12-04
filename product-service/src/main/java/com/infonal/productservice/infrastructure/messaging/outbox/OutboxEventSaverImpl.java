package com.infonal.productservice.infrastructure.messaging.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infonal.productservice.domain.event.DomainEvent;
import com.infonal.productservice.domain.event.ProductCreatedEvent;
import com.infonal.productservice.domain.model.AggregateRoot;
import com.infonal.productservice.infrastructure.messaging.mapper.ProductEventMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxEventSaverImpl implements OutboxEventSaver{
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final ProductEventMapper productEventMapper;


    public OutboxEventSaverImpl(OutboxRepository outboxRepository, ObjectMapper objectMapper, ProductEventMapper productEventMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
        this.productEventMapper = productEventMapper;
    }

    @Override
    public void saveEvents(AggregateRoot aggregateRoot) {
        List<DomainEvent> events = aggregateRoot.getDomainEvents();
        for(DomainEvent event : events){
            Object integrationEvent;
            String eventTypeForOutbox;
            if(event instanceof ProductCreatedEvent productCreatedEvent){
                // domain eventten integration event'e dönüşüm
                integrationEvent = productEventMapper.toIntegrationEvent(productCreatedEvent);
            }
            // diğer domain eventler buraya eklenmeye devam edecek
            else{
                // Olayın Integration Event'e karşılığı yoksa, bu bir altyapı hatasıdır.
                // Transaction'ı geri al
                throw new RuntimeException("Domain Event type not mapped for outbox serialization: " + event.getClass().getName());
            }
            try {
                // serileştirme
                String payloadJson = objectMapper.writeValueAsString(integrationEvent);
                OutboxMessage message = new OutboxMessage(
                        event.getEventId(), // domain event'in benzersiz id'si
                        aggregateRoot.getClass().getSimpleName(), // "Product"
                        aggregateRoot.getIdValue(),   // Product aggregate'in UUID değeri
                        event.getClass().getName(), // CreatedProductEvent
                        payloadJson
                );
                //  Outbox Tablosuna Kaydetme (Repository'yi kullanır)
                // Bu save işlemi, Application katmanındaki ana transaction'a katılır.
                outboxRepository.save(message);
            } catch (JsonProcessingException e){
                // Serileştirme hatası kritik bir iş hatasıdır ve Transaction'ı geri almalıdır (rollback).
                // Bu nedenle Unchecked Exception (Runtime) fırlatılır.
                throw new RuntimeException("Error serializing Domain Event for Outbox: " + event.getEventId(), e);
            }
        }
        // Olaylar artık kalıcı olarak Outbox tablosunda güvende olduğu için bellekteki listeyi temizle.
        aggregateRoot.clearDomainEvents();
    }
}

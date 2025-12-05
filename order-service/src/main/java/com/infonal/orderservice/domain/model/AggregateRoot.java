package com.infonal.orderservice.domain.model;

import com.infonal.orderservice.domain.event.DomainEvent;

import java.util.List;
import java.util.UUID;

public interface AggregateRoot {
    // 1. Olayları Dışa Aktarma Kontratı (Outbox için gereklidir)
    List<DomainEvent> getDomainEvents();

    // 2. Olayları Temizleme Kontratı (Outbox'a kaydedildikten sonra gereklidir)
    void clearDomainEvents();

    // 3. Aggregate ID'sini Alma Kontratı (OutboxMessage'da aggregateId olarak saklanır)
    UUID getIdValue();
}

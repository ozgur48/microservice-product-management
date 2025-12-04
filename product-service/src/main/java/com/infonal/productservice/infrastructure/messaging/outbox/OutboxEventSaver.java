package com.infonal.productservice.infrastructure.messaging.outbox;

import com.infonal.productservice.domain.model.AggregateRoot;

public interface OutboxEventSaver {
    // product aggregate'i içindeki domain eventleri outboxmessage olarak db'ye kaydeder
    void saveEvents(AggregateRoot aggregateRoot);
}

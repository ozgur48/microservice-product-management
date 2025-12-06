package com.infonal.orderservice.infrastructure.messaging.outbox;

import com.infonal.orderservice.domain.model.AggregateRoot;

public interface OutboxEventSaver {
    void saveEvents(AggregateRoot aggregateRoot);
}

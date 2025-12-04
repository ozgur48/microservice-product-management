package com.infonal.productservice.infrastructure.messaging.outbox;

public enum OutboxStatus {
    PENDING,
    SENT,
    FAILED
}

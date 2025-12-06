package com.infonal.orderservice.infrastructure.messaging.outbox;

public enum OutboxStatus {
    PENDING,
    SENT,
    FAILED
}
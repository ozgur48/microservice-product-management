package com.infonal.orderservice.infrastructure.outbox;

public enum OutboxStatus {
    PENDING,
    SENT,
    FAILED
}
package com.infonal.orderservice.infrastructure.messaging.outbox;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="outbox", indexes = {
        @Index(name = "ix_outbox_event_id", columnList = "eventId", unique = true),
        @Index(name = "ix_outbox_status_created", columnList = "status, createdAt")
})
public class OutboxMessage {
    @Id
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();

    @Column(nullable = false, columnDefinition = "uuid")
    private UUID eventId;

    @Column(nullable = false)
    private String aggregateType;
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID aggregateId;

    private String eventType;
    @Lob
    private String payloadJson;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OutboxStatus status = OutboxStatus.PENDING;
    private int retryCount = 0;

    private OffsetDateTime createdAt = OffsetDateTime.now();
    private OffsetDateTime processedAt;

    public OutboxMessage(UUID eventId, String aggregateType,
                         UUID aggregateId, String eventType, String payloadJson) {
        this.eventId = eventId;
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payloadJson = payloadJson;
    }
    // jpa için reflection (db'den) ile no-args constructor. db'den çekerken yeni bir nesne yaratmaz
    protected OutboxMessage(){}

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID eventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String aggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public UUID aggregateId() {
        return aggregateId;
    }

    public void setAggregateId(UUID aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String eventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String payloadJson() {
        return payloadJson;
    }

    public void setPayloadJson(String payloadJson) {
        this.payloadJson = payloadJson;
    }

    public OutboxStatus status() {
        return status;
    }

    public void setStatus(OutboxStatus status) {
        this.status = status;
    }

    public int retryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public OffsetDateTime createdAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime processedAt() {
        return processedAt;
    }

    public void setProcessedAt(OffsetDateTime processedAt) {
        this.processedAt = processedAt;
    }
}
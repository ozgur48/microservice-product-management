package com.infonal.orderservice.infrastructure.messaging.relayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infonal.orderservice.infrastructure.messaging.outbox.OutboxMessage;
import com.infonal.orderservice.infrastructure.messaging.outbox.OutboxRepository;
import com.infonal.orderservice.infrastructure.messaging.outbox.OutboxStatus;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OutboxEventRelayer {
    private final OutboxRepository outboxRepository;
    private final StreamBridge streamBridge;
    private final ObjectMapper objectMapper;

    private static final int MAX_RETRY_COUNT = 5;

    public OutboxEventRelayer(OutboxRepository outboxRepository, StreamBridge streamBridge, ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.streamBridge = streamBridge;
        this.objectMapper = objectMapper;
    }
    @Scheduled(fixedDelayString = "${outbox.scheduler.fixed-delay:5000}")
    @Transactional
    public void publishPendingEvents(){
        List<OutboxMessage> pendingEvents = outboxRepository.findByStatusOrderByCreatedAtAsc(OutboxStatus.PENDING);
        if(pendingEvents.isEmpty()){
            return;
        }
        for(OutboxMessage pending: pendingEvents){
            boolean successfullySent = false;
            try{

                // 2. KAFKA MESAJINI OLUŞTUR
                // Kafka Key ve Idempotency için eventId'yi header olarak eklemek önemlidir.
                // streamBridge, Spring Cloud Stream'in çıktı binding'ine gönderir.
                Message<?> message = MessageBuilder.withPayload(pending.payloadJson())
                        .setHeader("event-id", pending.eventId().toString())
                        .setHeader("aggregate-id", pending.aggregateId().toString())
                        .build();
                successfullySent = streamBridge.send(pending.aggregateType().toLowerCase() + "Events-out-0", message);
            }catch (Exception e){
                successfullySent = false;
            }
            if(successfullySent){
                pending.setStatus(OutboxStatus.SENT);
                pending.setProcessedAt(OffsetDateTime.now());
            }else{
                pending.setRetryCount(pending.retryCount() + 1);
                if(pending.retryCount() > MAX_RETRY_COUNT){
                    pending.setStatus(OutboxStatus.FAILED);
                }
            }
            outboxRepository.save(pending);
        }
    }
}


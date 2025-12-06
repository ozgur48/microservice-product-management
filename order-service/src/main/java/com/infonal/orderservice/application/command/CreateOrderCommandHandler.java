package com.infonal.orderservice.application.command;

import com.infonal.orderservice.application.dto.CreatedOrderResponse;
import com.infonal.orderservice.application.mapper.CreateOrderMapper;
import com.infonal.orderservice.cqrs.CommandHandler;
import com.infonal.orderservice.domain.model.*;
import com.infonal.orderservice.domain.port.OrderRepository;
import com.infonal.orderservice.infrastructure.messaging.outbox.OutboxEventSaver;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand, CreatedOrderResponse> {
    private final OrderRepository orderRepository;
    private final CreateOrderMapper createOrderMapper;
    private final OutboxEventSaver outboxEventSaver;

    public CreateOrderCommandHandler(OrderRepository orderRepository, CreateOrderMapper createOrderMapper, OutboxEventSaver outboxEventSaver) {
        this.orderRepository = orderRepository;
        this.createOrderMapper = createOrderMapper;
        this.outboxEventSaver = outboxEventSaver;
    }

    @Override
    @Transactional
    public CreatedOrderResponse handle(CreateOrderCommand command) {
        // command'tan domaine dönüşüm
        OrderId orderId = new OrderId(command.orderId());
        ProductId productId = new ProductId(command.productId());
        Quantity quantity = new Quantity(command.quantity());
        Address address = new Address(command.city(), command.district());
        Money money = new Money(command.amount(), command.currency());
        Order order = Order.create(
                productId,
                quantity,
                address,
                money
        );
        orderRepository.save(order);

        outboxEventSaver.saveEvents(order);

        return createOrderMapper.toResponse(order);
    }
}

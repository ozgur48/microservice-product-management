package com.infonal.orderservice.application.command;

import com.infonal.orderservice.application.dto.DeletedOrderResponse;
import com.infonal.orderservice.cqrs.CommandHandler;
import com.infonal.orderservice.domain.exception.OrderNotFound;
import com.infonal.orderservice.domain.model.OrderId;
import com.infonal.orderservice.domain.port.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteOrderCommandHandler implements CommandHandler<DeleteOrderCommand, DeletedOrderResponse> {
    private final OrderRepository orderRepository;

    public DeleteOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public DeletedOrderResponse handle(DeleteOrderCommand command) {
        OrderId orderId = new OrderId(command.id());
        orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFound(orderId.id()));
        return new DeletedOrderResponse(orderId.id());
    }
}

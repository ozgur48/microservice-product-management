package com.infonal.orderservice.application.command;

import com.infonal.orderservice.application.dto.UpdatedOrderAddressResponse;
import com.infonal.orderservice.application.mapper.UpdateOrderAddressMapper;
import com.infonal.orderservice.cqrs.CommandHandler;
import com.infonal.orderservice.domain.exception.OrderNotFound;
import com.infonal.orderservice.domain.model.Address;
import com.infonal.orderservice.domain.model.Order;
import com.infonal.orderservice.domain.model.OrderId;
import com.infonal.orderservice.domain.port.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateOrderAddressCommandHandler implements CommandHandler<UpdateOrderAddressCommand, UpdatedOrderAddressResponse> {
    private final OrderRepository orderRepository;
    private final UpdateOrderAddressMapper mapper;

    public UpdateOrderAddressCommandHandler(OrderRepository orderRepository, UpdateOrderAddressMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public UpdatedOrderAddressResponse handle(UpdateOrderAddressCommand command) {
        Order order = orderRepository
                .findById(new OrderId(command.orderId()))
                .orElseThrow(()-> new OrderNotFound(command.orderId()));
        order.changeShippingAddress(new Address(command.city(), command.district()));
        orderRepository.save(order);
        return mapper.toResponse(order);
    }
}

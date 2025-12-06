package com.infonal.orderservice.application.mapper;

import com.infonal.orderservice.application.dto.CreatedOrderResponse;
import com.infonal.orderservice.application.dto.OrderStatusDto;
import com.infonal.orderservice.domain.model.Order;
import com.infonal.orderservice.domain.model.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderMapper {
    public CreatedOrderResponse toResponse(Order order){
        OrderStatusDto orderStatusDto = OrderStatusDto.valueOf(order.orderStatus().name());
        return new CreatedOrderResponse(
                order.orderId().id(),
                order.productId().id(),
                order.quantity().value(),
                order.address().city(),
                order.address().district(),
                order.orderDate().value(),
                order.money().amount(),
                order.money().currency(),
                orderStatusDto.name()
        );
    }
}

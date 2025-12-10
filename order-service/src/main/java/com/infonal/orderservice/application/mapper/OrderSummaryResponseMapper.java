package com.infonal.orderservice.application.mapper;

import com.infonal.orderservice.application.dto.OrderStatusDto;
import com.infonal.orderservice.application.dto.OrderSummaryResponse;
import com.infonal.orderservice.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderSummaryResponseMapper {
    public OrderSummaryResponse toResponse(Order order){
        OrderStatusDto orderStatusDto = OrderStatusDto.valueOf(order.orderStatus().name());

        return new OrderSummaryResponse(
                order.orderId().id(),
                order.orderDate().value(),
                order.money().amount(),
                order.money().currency(),
                orderStatusDto
        );
    }
}

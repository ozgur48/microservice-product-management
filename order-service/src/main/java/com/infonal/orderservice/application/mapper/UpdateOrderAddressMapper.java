package com.infonal.orderservice.application.mapper;

import com.infonal.orderservice.application.dto.UpdatedOrderAddressResponse;
import com.infonal.orderservice.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class UpdateOrderAddressMapper {
    public UpdatedOrderAddressResponse toResponse(Order order){
        return new UpdatedOrderAddressResponse(
                order.orderId().id(),
                order.address().city(),
                order.address().district()
        );
    }
}

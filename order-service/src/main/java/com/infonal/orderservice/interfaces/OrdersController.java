package com.infonal.orderservice.interfaces;

import com.infonal.orderservice.application.command.CreateOrderCommand;
import com.infonal.orderservice.application.dto.CreatedOrderResponse;
import com.infonal.orderservice.cqrs.CommandHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/orders")
public class OrdersController {
    private final CommandHandler<CreateOrderCommand, CreatedOrderResponse> createdOrderCommandHandler;

    public OrdersController(CommandHandler<CreateOrderCommand, CreatedOrderResponse> createdOrderCommandHandler) {
        this.createdOrderCommandHandler = createdOrderCommandHandler;
    }

    @ResponseStatus(HttpStatus.CREATED)
    public CreatedOrderResponse createOrder(@Valid @RequestBody CreateOrderCommand command){
        return createdOrderCommandHandler.handle(command);
    }
}

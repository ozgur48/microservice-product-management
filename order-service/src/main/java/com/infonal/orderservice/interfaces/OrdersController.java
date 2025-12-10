package com.infonal.orderservice.interfaces;

import com.infonal.orderservice.application.command.CreateOrderCommand;
import com.infonal.orderservice.application.command.DeleteOrderCommand;
import com.infonal.orderservice.application.command.UpdateOrderAddressCommand;
import com.infonal.orderservice.application.dto.*;
import com.infonal.orderservice.application.query.FindAllOrdersQuery;
import com.infonal.orderservice.cqrs.CommandHandler;
import com.infonal.orderservice.cqrs.QueryHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@Validated
public class OrdersController {
    private final CommandHandler<CreateOrderCommand, CreatedOrderResponse> createdOrderCommandHandler;
    private final CommandHandler<DeleteOrderCommand, DeletedOrderResponse> deleteOrderCommandHandler;
    private final CommandHandler<UpdateOrderAddressCommand, UpdatedOrderAddressResponse> updateOrderAddressCommandHandler;
    private final QueryHandler<FindAllOrdersQuery, PagedResponse<OrderSummaryResponse>> orderSummaryQueryHandler;

    public OrdersController(CommandHandler<CreateOrderCommand, CreatedOrderResponse> createdOrderCommandHandler, CommandHandler<DeleteOrderCommand, DeletedOrderResponse> deleteOrderCommandHandler, CommandHandler<UpdateOrderAddressCommand, UpdatedOrderAddressResponse> updateOrderAddressCommandHandler, QueryHandler<FindAllOrdersQuery, PagedResponse<OrderSummaryResponse>> orderSummaryQueryHandler) {
        this.createdOrderCommandHandler = createdOrderCommandHandler;
        this.deleteOrderCommandHandler = deleteOrderCommandHandler;
        this.updateOrderAddressCommandHandler = updateOrderAddressCommandHandler;
        this.orderSummaryQueryHandler = orderSummaryQueryHandler;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreatedOrderResponse createOrder(@Valid @RequestBody CreateOrderCommand command){
        return createdOrderCommandHandler.handle(command);
    }
    @PutMapping("/{id}")
    public UpdatedOrderAddressResponse updateOrderAddress(@Valid @RequestBody UpdateOrderAddressCommand command, @PathVariable UUID id){
        UpdateOrderAddressCommand finalCommand = new UpdateOrderAddressCommand(
                id,
                command.city(),
                command.district()
                );
        return updateOrderAddressCommandHandler.handle(finalCommand);
    }

    @GetMapping
    public PagedResponse<OrderSummaryResponse> findAllOrders(@Valid FindAllOrdersQuery query){
        return orderSummaryQueryHandler.handle(query);
    }

    @DeleteMapping("/{id}")
    public DeletedOrderResponse deleteOrder(@Valid @PathVariable UUID id, DeleteOrderCommand command){
        DeleteOrderCommand finalCommand = new DeleteOrderCommand(id);
        return deleteOrderCommandHandler.handle(finalCommand);
    }
}

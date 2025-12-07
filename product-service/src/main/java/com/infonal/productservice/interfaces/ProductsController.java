package com.infonal.productservice.interfaces;

import com.infonal.productservice.application.command.CreateProductCommand;
import com.infonal.productservice.application.command.UpdateProductStockCommand;
import com.infonal.productservice.application.dto.CreatedProductResponse;
import com.infonal.productservice.application.dto.UpdatedProductStockResponse;
import com.infonal.productservice.core.cqrs.CommandHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductsController {
    private final CommandHandler<CreateProductCommand, CreatedProductResponse> createProductCommandHandler;
    private final CommandHandler<UpdateProductStockCommand, UpdatedProductStockResponse> updatedProductStockResponseCommandHandler;

    public ProductsController(CommandHandler<CreateProductCommand, CreatedProductResponse> createProductCommandHandler, CommandHandler<UpdateProductStockCommand, UpdatedProductStockResponse> updatedProductStockResponseCommandHandler) {
        this.createProductCommandHandler = createProductCommandHandler;
        this.updatedProductStockResponseCommandHandler = updatedProductStockResponseCommandHandler;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse createProduct(@Valid @RequestBody CreateProductCommand command){
        return createProductCommandHandler.handle(command);
    }

    @PutMapping("/{id}")
    public UpdatedProductStockResponse updateProductStock(@Valid @PathVariable UUID id, @RequestBody UpdateProductStockCommand command){
        UpdateProductStockCommand finalCommand = new UpdateProductStockCommand(
                id,
                command.newStock()
        );
        return updatedProductStockResponseCommandHandler.handle(finalCommand);
    }
}

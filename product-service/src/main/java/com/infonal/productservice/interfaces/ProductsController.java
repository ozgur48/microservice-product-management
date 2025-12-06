package com.infonal.productservice.interfaces;

import com.infonal.productservice.application.command.CreateProductCommand;
import com.infonal.productservice.application.dto.CreatedProductResponse;
import com.infonal.productservice.core.cqrs.CommandHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductsController {
    private final CommandHandler<CreateProductCommand, CreatedProductResponse> createProductCommandHandler;

    public ProductsController(CommandHandler<CreateProductCommand, CreatedProductResponse> createProductCommandHandler) {
        this.createProductCommandHandler = createProductCommandHandler;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse createProduct(@Valid @RequestBody CreateProductCommand command){
        return createProductCommandHandler.handle(command);
    }
}
.
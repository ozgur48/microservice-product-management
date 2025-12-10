package com.infonal.productservice.application.command;

import com.infonal.productservice.application.dto.DeletedProductResponse;
import com.infonal.productservice.core.cqrs.CommandHandler;
import com.infonal.productservice.domain.exception.ProductNotFound;
import com.infonal.productservice.domain.model.ProductId;
import com.infonal.productservice.domain.port.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductCommandHandler implements CommandHandler<DeleteProductCommand, DeletedProductResponse> {
    private final ProductRepository productRepository;

    public DeleteProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public DeletedProductResponse handle(DeleteProductCommand command) {
        ProductId productId = new ProductId(command.id());
        productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFound(command.id()));
        productRepository.delete(productId);
        return new DeletedProductResponse(command.id());
    }
}

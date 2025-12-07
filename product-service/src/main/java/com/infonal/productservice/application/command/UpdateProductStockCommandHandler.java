package com.infonal.productservice.application.command;

import com.infonal.productservice.application.dto.UpdatedProductStockResponse;
import com.infonal.productservice.application.mapper.UpdateProductStockMapper;
import com.infonal.productservice.core.cqrs.CommandHandler;
import com.infonal.productservice.domain.exception.ProductNotFound;
import com.infonal.productservice.domain.model.Product;
import com.infonal.productservice.domain.model.ProductId;
import com.infonal.productservice.domain.port.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductStockCommandHandler implements CommandHandler<UpdateProductStockCommand, UpdatedProductStockResponse> {
    private final ProductRepository productRepository;
    private final UpdateProductStockMapper updateProductStockMapper;

    public UpdateProductStockCommandHandler(ProductRepository productRepository, UpdateProductStockMapper updateProductStockMapper) {
        this.productRepository = productRepository;
        this.updateProductStockMapper = updateProductStockMapper;
    }

    @Override
    public UpdatedProductStockResponse handle(UpdateProductStockCommand command) {
        Product product = productRepository
                .findById(new ProductId(command.productId()))
                .orElseThrow(()-> new ProductNotFound(command.productId()));
        product.updateStock(command.newStock());
        productRepository.save(product);
        return updateProductStockMapper.toResponse(product);
    }
}

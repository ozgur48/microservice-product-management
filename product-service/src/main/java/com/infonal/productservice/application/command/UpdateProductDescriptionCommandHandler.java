package com.infonal.productservice.application.command;

import com.infonal.productservice.application.dto.UpdatedProductDescriptionResponse;
import com.infonal.productservice.application.mapper.UpdatedProductDescriptionMapper;
import com.infonal.productservice.core.cqrs.CommandHandler;
import com.infonal.productservice.domain.exception.ProductNotFound;
import com.infonal.productservice.domain.model.Description;
import com.infonal.productservice.domain.model.Product;
import com.infonal.productservice.domain.model.ProductId;
import com.infonal.productservice.domain.port.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductDescriptionCommandHandler implements CommandHandler<UpdateProductDescriptionCommand, UpdatedProductDescriptionResponse> {
    private final ProductRepository productRepository;
    private final UpdatedProductDescriptionMapper updatedProductDescriptionMapper;

    public UpdateProductDescriptionCommandHandler(ProductRepository productRepository, UpdatedProductDescriptionMapper updatedProductDescriptionMapper) {
        this.productRepository = productRepository;
        this.updatedProductDescriptionMapper = updatedProductDescriptionMapper;
    }

    @Override
    public UpdatedProductDescriptionResponse handle(UpdateProductDescriptionCommand command) {
        Product product = productRepository
                .findById(new ProductId(command.productId()))
                .orElseThrow(()-> new ProductNotFound(command.productId()));
        product.updateDescription(new Description(command.newDescription()));
        productRepository.save(product);
        return updatedProductDescriptionMapper.toResponse(product);
    }
}

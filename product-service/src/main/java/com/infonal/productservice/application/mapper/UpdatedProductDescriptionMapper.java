package com.infonal.productservice.application.mapper;

import com.infonal.productservice.application.dto.UpdatedProductDescriptionResponse;
import com.infonal.productservice.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public record UpdatedProductDescriptionMapper() {
    public UpdatedProductDescriptionResponse toResponse(Product product){
        return new UpdatedProductDescriptionResponse(
                product.productId().value(),
                product.description().value()
        );
    }
}

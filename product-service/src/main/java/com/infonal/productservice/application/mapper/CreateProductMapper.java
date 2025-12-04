package com.infonal.productservice.application.mapper;

import com.infonal.productservice.application.dto.CreatedProductResponse;
import com.infonal.productservice.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class CreateProductMapper {
    public CreatedProductResponse toResponse(Product product){
        return new CreatedProductResponse(
                product.productId().value(),
                product.productName().value(),
                product.description().value(),
                product.stock().value(),
                product.money().amount(),
                product.money().currency()
        );
    }
}

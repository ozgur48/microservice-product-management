package com.infonal.productservice.application.mapper;

import com.infonal.productservice.application.dto.UpdatedProductStockResponse;
import com.infonal.productservice.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductStockMapper {
    public UpdatedProductStockResponse toResponse(Product product){
        return new UpdatedProductStockResponse(
                product.productId().value(),
                product.stock().value()
        );
    }
}

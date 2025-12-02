package com.infonal.productservice.infrastructure;

import com.infonal.productservice.domain.model.*;
import org.springframework.stereotype.Component;

/**
 * Product Mapper
 * Domain entity ile Infrastructure entity arasında dönüşüm sağlar
 */
@Component
public class JpaProductMapper {
    /**
     * Domain entity'den JPA entity'ye dönüşüm
     */
    public JpaProductEntity toEntity(Product product){
        return new JpaProductEntity(
                product.productId().value(),
                product.productName().value(),
                product.description().value(),
                product.stock().value(),
                product.money().amount(),
                product.money().currency()
        );
    }
    public Product toDomain(JpaProductEntity entity){
        return Product.rehdyrate(
                new ProductId(entity.id()),
                new ProductName(entity.productName()),
                new Description(entity.description()),
                new Stock(entity.stock()),
                new Money(entity.priceAmount(),entity.priceCurrency())
        );
    }
}

package com.infonal.productservice.domain.model;

import com.infonal.productservice.domain.event.DiscountEvent;
import com.infonal.productservice.domain.event.DomainEvent;
import com.infonal.productservice.domain.event.ProductCreatedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Product implements AggregateRoot{
    private final ProductId productId;

    private ProductName productName;
    private Description description;
    private Stock stock;
    private Money money;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Product(ProductId productId, ProductName productName,
                   Description description, Stock stock, Money money) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.stock = stock;
        this.money = money;
    }

    public static Product create(ProductName productName,
                                 Description description, Stock stock, Money money){

        ProductId newProductId = ProductId.generate();
        Product product = new Product(
                newProductId,
                productName,
                description,
                stock,
                money
        );
        // product.domainEvents.add()
        product.domainEvents.add(
                new ProductCreatedEvent(product.productId, product.productName,
                        product.description, product.stock, product.money)
        );
        return product;
    }
    public static Product rehdyrate(ProductId productId, ProductName productName,
                                    Description description, Stock stock, Money money ){
        return new Product(
                productId, productName,
                description, stock,
                money
        );
    }
    public void changeDescription(Description newDescription){
        this.description = newDescription;
    }

    public void applyDiscount(Money discountAmount){
        Money newPrice = this.money.subtract(discountAmount);
        this.money = newPrice;
        domainEvents.add(
                new DiscountEvent(
                        this.productId,
                        this.productName,
                        newPrice
                )
        );
    }


    @Override
    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    @Override
    public void clearDomainEvents() {
        this.domainEvents.clear();
    }

    @Override
    public UUID getIdValue() {
        return this.productId.value();
    }
}

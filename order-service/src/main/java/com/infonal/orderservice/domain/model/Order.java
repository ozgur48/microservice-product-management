package com.infonal.orderservice.domain.model;

import com.infonal.orderservice.domain.event.DomainEvent;
import com.infonal.orderservice.domain.event.OrderCreatedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order implements AggregateRoot{
    private final OrderId orderId;
    private ProductId productId;
    private Quantity quantity;
    private Address address;
    private OrderDate orderDate;
    private Money money;
    private OrderStatus orderStatus;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Order(OrderId orderId, ProductId productId,
                 Quantity quantity, Address address,
                 OrderDate orderDate, Money money,
                 OrderStatus orderStatus) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.address = address;
        this.orderDate = orderDate;
        this.money = money;
        this.orderStatus = orderStatus;
    }
    public static Order create(ProductId productId, Quantity quantity, Address address,
                               OrderDate orderDate, Money money,
                               OrderStatus orderStatus){
        OrderId newOrderId = OrderId.generate();
        Order order = new Order(
                newOrderId,
                productId,
                quantity,
                address,
                orderDate,
                money,
                orderStatus
        );
        order.domainEvents.add(
                new OrderCreatedEvent(
                        order.orderId,
                        order.productId,
                        order.quantity,
                        order.address,
                        order.orderDate,
                        order.money,
                        order.orderStatus
                )
        );
        return order;
    }

    public OrderId orderId() {
        return orderId;
    }

    public ProductId productId() {
        return productId;
    }

    public Quantity quantity() {
        return quantity;
    }

    public Address address() {
        return address;
    }

    public OrderDate orderDate() {
        return orderDate;
    }

    public Money money() {
        return money;
    }

    public OrderStatus orderStatus() {
        return orderStatus;
    }

    public List<DomainEvent> domainEvents() {
        return domainEvents;
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
        return this.orderId.id();
    }
}

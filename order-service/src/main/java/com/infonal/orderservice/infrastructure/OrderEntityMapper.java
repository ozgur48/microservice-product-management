package com.infonal.orderservice.infrastructure;

import com.infonal.orderservice.domain.model.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityMapper {
    public JpaOrderEntity toEntity(Order order){
        return new JpaOrderEntity(
                order.orderId().id(),
                order.productId().id(),
                order.quantity().value(),
                order.address().city(),
                order.address().district(),
                order.orderDate().value(),
                order.money().amount(),
                order.money().currency(),
                order.orderStatus()
        );
    }
    public Order toDomain(JpaOrderEntity entity){
        return Order.rehdydrate(
                new OrderId(entity.id()),
                new ProductId(entity.productId()),
                new Quantity(entity.quantity()),
                new Address(entity.city(), entity.district()),
                new OrderDate(entity.orderDate()),
                new Money(entity.amount(), entity.currency()),
                entity.status()
        );
    }
}

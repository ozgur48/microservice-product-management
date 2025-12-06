package com.infonal.orderservice.infrastructure;

import com.infonal.orderservice.domain.model.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class JpaOrderEntity {
    @Id
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, columnDefinition = "uuid")
    private UUID productId;

    @Column(nullable = false)
    @Positive
    private int quantity;

    @Column(nullable = false)
    @Size(min = 3, max = 100)
    private String city;

    @Column(nullable = false, length = 100)
    @Size(min = 3, max = 100)
    private String district;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Positive
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;


    public JpaOrderEntity(UUID id, UUID productId, int quantity,
                          String city, String district, LocalDateTime orderDate,
                          BigDecimal amount, String currency, OrderStatus status) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.city = city;
        this.district = district;
        this.orderDate = orderDate;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
    }
    public JpaOrderEntity(){}

    public UUID id() {
        return id;
    }

    public UUID productId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int quantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String city() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String district() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public LocalDateTime orderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal amount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String currency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public OrderStatus status() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

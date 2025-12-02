package com.infonal.productservice.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="products")
public class JpaProductEntity {
    @Id
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, length = 255)
    private String productName;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal priceAmount;

    @Column(nullable = false, length = 3)
    private String priceCurrency;

    public JpaProductEntity(UUID id, String productName, String description, int stock, BigDecimal priceAmount, String priceCurrency) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.stock = stock;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
    }
    public JpaProductEntity(){}

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String productName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int stock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal priceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String priceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }
}

package com.infonal.productservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<JpaProductEntity, UUID>{
}

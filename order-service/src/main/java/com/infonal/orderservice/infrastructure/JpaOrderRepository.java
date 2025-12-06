package com.infonal.orderservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface JpaOrderRepository extends JpaRepository<JpaOrderEntity, UUID> {
}

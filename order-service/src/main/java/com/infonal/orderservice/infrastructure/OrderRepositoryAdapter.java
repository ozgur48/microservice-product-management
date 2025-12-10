package com.infonal.orderservice.infrastructure;

import com.infonal.orderservice.domain.model.Order;
import com.infonal.orderservice.domain.model.OrderId;
import com.infonal.orderservice.domain.port.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {
    private final JpaOrderRepository jpaOrderRepository;
    private final OrderEntityMapper entityMapper;

    public OrderRepositoryAdapter(JpaOrderRepository jpaOrderRepository, OrderEntityMapper entityMapper) {
        this.jpaOrderRepository = jpaOrderRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public Order save(Order order) {
        JpaOrderEntity entity = entityMapper.toEntity(order);
        entity = jpaOrderRepository.save(entity);
        return entityMapper.toDomain(entity);
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return jpaOrderRepository.findById(orderId.id()).map(entityMapper::toDomain);
    }

    @Override
    public List<Order> findAllPaged(Integer pageIndex, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<JpaOrderEntity> entityPage = jpaOrderRepository.findAll(pageRequest);
        return entityPage.stream().map(entityMapper::toDomain).toList();
    }

    @Override
    public void delete(OrderId orderId) {
        jpaOrderRepository.deleteById(orderId.id());
    }

    @Override
    public long countAll() {
        return jpaOrderRepository.count();
    }
}

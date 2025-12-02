package com.infonal.productservice.infrastructure;

import com.infonal.productservice.domain.model.Product;
import com.infonal.productservice.domain.model.ProductId;
import com.infonal.productservice.domain.port.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductRepositoryAdapter implements ProductRepository {
    private final JpaProductMapper productMapper;
    private final JpaProductRepository repository;

    public ProductRepositoryAdapter(JpaProductMapper productMapper, JpaProductRepository repository) {
        this.productMapper = productMapper;
        this.repository = repository;
    }


    @Override
    public Product save(Product product) {
        JpaProductEntity entity = productMapper.toEntity(product);
        entity = repository.save(entity);
        return productMapper.toDomain(entity);
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return repository.findById(productId.value()).map(productMapper::toDomain);
    }

    @Override
    public List<Product> findAllPaged(int pageIndex, int pageSize) {
        return repository.findAll().stream().map(productMapper::toDomain).toList();
    }

    @Override
    public void delete(ProductId productId) {
        repository.deleteById(productId.value());
    }
}

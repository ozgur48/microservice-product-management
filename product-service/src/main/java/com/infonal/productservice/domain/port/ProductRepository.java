package com.infonal.productservice.domain.port;

import com.infonal.productservice.domain.model.Product;
import com.infonal.productservice.domain.model.ProductId;

import java.util.List;
import java.util.Optional;


public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(ProductId productId);
    List<Product> findAllPaged(int pageIndex, int pageSize);
    void delete(ProductId productId);
}

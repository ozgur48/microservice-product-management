package com.infonal.productservice.application.eventHandlers;

import com.infonal.productservice.domain.exception.ProductNotFound;
import com.infonal.productservice.domain.model.Product;
import com.infonal.productservice.domain.model.ProductId;
import com.infonal.productservice.domain.port.ProductRepository;
import com.infonal.productservice.infrastructure.messaging.events.OrderCreatedIntegrationEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductOrderService {
    private final ProductRepository productRepository;

    public ProductOrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional
    public void processOrderCreation(OrderCreatedIntegrationEvent event){
        ProductId productId = new ProductId(event.productId()); // gelen uuid'yi vo'ya cevir
        // aggregate'i yükle
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFound(productId.value()));
        product.reserveStock(event.quantity());
        productRepository.save(product);
    }
}

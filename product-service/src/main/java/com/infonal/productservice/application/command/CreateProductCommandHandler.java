package com.infonal.productservice.application.command;

import com.infonal.productservice.application.dto.CreatedProductResponse;
import com.infonal.productservice.application.mapper.CreateProductMapper;
import com.infonal.productservice.core.cqrs.CommandHandler;
import com.infonal.productservice.domain.model.*;
import com.infonal.productservice.domain.port.ProductRepository;
import com.infonal.productservice.infrastructure.messaging.outbox.OutboxEventSaver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateProductCommandHandler implements CommandHandler<CreateProductCommand, CreatedProductResponse> {
    private final ProductRepository productRepository;
    private final CreateProductMapper mapper;
    private final OutboxEventSaver outboxEventSaver;

    public CreateProductCommandHandler(ProductRepository productRepository, CreateProductMapper mapper, OutboxEventSaver outboxEventSaver) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.outboxEventSaver = outboxEventSaver;
    }

    /**
     * product create komutunu işler.
     * Bu metot, Product kaydı ve Outbox kaydını tek bir veritabanı transaction'ında tutar.
     */
    @Transactional
    @Override
    public CreatedProductResponse handle(CreateProductCommand command) {
        // dısarıdan gelen nesneyi domain nesnesine döndürüyoruz
        ProductName productName = new ProductName(command.productName());
        Description description = new Description(command.description());
        Stock stock = new Stock(command.stock());
        Money money = new Money(command.amount(), command.currency());


        // domain aggregate'i olan product olusturuluyor
        Product product = Product.create(
                productName,
                description,
                stock,
                money
        );
        // Aggregate'i Kaydetme (Atomik Transaction'ın İlk Parçası)
        productRepository.save(product);

        // Domain Event'leri Outbox Tablosuna Kaydetme (Atomik Transaction'ın İkinci Parçası)
        outboxEventSaver.saveEvents(product);

        return mapper.toResponse(product);
    }
}

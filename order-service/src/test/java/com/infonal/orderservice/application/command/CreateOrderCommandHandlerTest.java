package com.infonal.orderservice.application.command;

import com.infonal.orderservice.application.dto.CreatedOrderResponse;
import com.infonal.orderservice.application.mapper.CreateOrderMapper;
import com.infonal.orderservice.domain.model.Order;
import com.infonal.orderservice.domain.port.OrderRepository;
import com.infonal.orderservice.infrastructure.messaging.outbox.OutboxEventSaver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CreateOrderCommandHandlerTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CreateOrderMapper mapper;

    @Mock
    private OutboxEventSaver outboxEventSaver;

    @InjectMocks
    private CreateOrderCommandHandler handler;

    // Handler, Command'den aldığı verileri Order.create metoduna doğru
    @Captor
    private ArgumentCaptor<Order> orderCaptor; // İçeride oluşturulan Order'ı yakalamak için

    private CreateOrderCommand command; // Ortak nesne

    @BeforeEach
    void setUp(){
        // Her testte değişmeyecek temel veriyi burada hazırlıyoruz
        command = new CreateOrderCommand(UUID.randomUUID(),
                UUID.randomUUID(),
                5,
                "Ortaca",
                "Muğla",
                new BigDecimal(1000),
                "USD" );
    }
    @Test
    void whenHandleCreateOrder_thenOrderShouldBeSaved(){
       // arrange
        // Mock Response Hazırlığı
        CreatedOrderResponse mockResponse = new CreatedOrderResponse(
                command.orderId(),
                command.productId(),
                command.quantity(),
                command.city(),
                command.district(),
                LocalDateTime.now(), // Sipariş tarihi şu an
                command.amount(),
                command.currency(),
                "CREATED" // Statüyü domain'de ne setlediysen o
        );
        // "Mapper'a herhangi bir Order nesnesi gelirse, yukarıdaki mockResponse'u dön."
        when(mapper.toResponse(any(Order.class))).thenReturn(mockResponse);


        // ACT
        CreatedOrderResponse actualResponse = handler.handle(command);

        // ASSERT & VERIFY
        // 1. Repository'ye kaydedilen nesneyi yakalıyoruz
        verify(orderRepository).save(orderCaptor.capture());
        Order capturedOrder = orderCaptor.getValue();

        // "Argument Capturing"
        // 2. İçerideki dönüşümün doğruluğunu kontrol ediyoruz (En önemli kısım burası!)
        assertThat(capturedOrder.quantity().value()).isEqualTo(5);
        assertThat(capturedOrder.address().city()).isEqualTo("Ortaca");
        assertThat(capturedOrder.address().district()).isEqualTo("Muğla");
        assertThat(capturedOrder.money().amount()).isEqualByComparingTo(new BigDecimal(1000));
        assertThat(capturedOrder.money().currency()).isEqualTo("USD");

        // Dışarıya dönen response'un mapper'dan gelenle aynı olduğunu test ediyoruz
        assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("OrderDate") // olusabilecek zaman farkından dolayı
                .isEqualTo(mockResponse);

        // 3. Outbox ve Mapper çağrılarını doğruluyoruz
        // Davranış Doğrulama createordermapper gerçekten çağrıldı mı?
        verify(mapper, times(1)).toResponse(any(Order.class));
        // Yakaladığımız order mı gönderildi?
        verify(outboxEventSaver).saveEvents(capturedOrder);

    }
}

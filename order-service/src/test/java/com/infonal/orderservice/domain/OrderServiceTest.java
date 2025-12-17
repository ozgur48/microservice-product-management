package com.infonal.orderservice.domain;

import com.infonal.orderservice.application.dto.OrderStatusDto;
import com.infonal.orderservice.application.dto.OrderSummaryResponse;
import com.infonal.orderservice.application.mapper.OrderSummaryResponseMapper;
import com.infonal.orderservice.application.query.FindAllOrdersQuery;
import com.infonal.orderservice.application.query.FindAllOrdersQueryHandler;
import com.infonal.orderservice.domain.model.Order;
import com.infonal.orderservice.domain.port.OrderRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;



import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    // dependency injection
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderSummaryResponseMapper mapper;

    // test edilecek gerçek sınıf
    @InjectMocks
    private FindAllOrdersQueryHandler handler;



    private FindAllOrdersQuery query;
    private List<Order> mockOrderContent;
    private Page<Order> mockOrderPage;
    private List<OrderSummaryResponse> exptectedResponse;

    private static final UUID ORDER_UUID_A = UUID.fromString("a1b2c3d4-0000-0000-0000-000000000001");
    private static final UUID ORDER_UUID_B = UUID.fromString("a1b2c3d4-0000-0000-0000-000000000002");

    @BeforeEach
    void setUp(){
        // --- 1. GİRDİ (Query Nesnesi) ---
        // Query nesnesi, sayfalama parametrelerini içermeli
        query = new FindAllOrdersQuery(0,10);

        // 2. DOMAIN NESNELERİ (Repository'den gelecek)
        Order orderA = mock(Order.class);
        Order orderB = mock(Order.class);
        mockOrderContent = Arrays.asList(orderA, orderB);

        // Mock Page objesi oluşturma (Repository'den bu dönecek)
        // PageImpl, Page arayüzünün bir uygulamasıdır. İçerik ve Pageable bilgisini alır.

        mockOrderPage = new PageImpl<>(
                mockOrderContent,
                PageRequest.of(query.pageIndex(), query.pageSize()),
                2L // toplam eleman sayısı
        );
        // 3. DTO NESNELERİ (Mapper'dan gelecek, Handler'ın çıktısı)
        OrderSummaryResponse responseA = new OrderSummaryResponse(ORDER_UUID_A, LocalDateTime.now(),
                new BigDecimal(1000), "USD", OrderStatusDto.DELIVERED);

        OrderSummaryResponse responseB = new OrderSummaryResponse(ORDER_UUID_A, LocalDateTime.now(),
                new BigDecimal(2000), "EUR", OrderStatusDto.CANCELLED);

        exptectedResponse = Arrays.asList(responseA, responseB);

    }

    // Mockito Talimatı: Repository'nin findAll metodu herhangi bir Pageable objesiyle çağrıldığında,
    // mockOrderPage objesini döndür.

    @Test
    void whenHandleQuery_thenOrdersShouldBeFetchedAndMapped(){
        // --- 1 hazırlık arrange
        // a) Repository Talimatı: findAll metodu herhangi bir Pageable objesiyle çağrıldığında, mockOrderPage'i döndür.
        // NOT: Repository metodunuzun isminin 'findAllPaged' değil, Spring Data'da yaygın olan 'findAll' olduğunu varsayıyoruz.
        // Eğer 'findAllPaged' ise, aşağıdaki kodu güncelleyin.
        // ArgumentMatchers.anyInt() kullanarak, gelen değerlerin Integer tipinde herhangi bir değer olabileceğini belirtiyoruz.
        // orderRepository, @Mock ile tanımlanmış bir Mockito nesnesidir.
        doReturn(mockOrderContent)
                .when(orderRepository)
                .findAllPaged(anyInt(), anyInt());
        // Mapper Talimatı: Mapper'a Order listesi verildiğinde, exptectedResponse'u döndür.
        // Mapper Talimatı (Bu satırda sorun yok)
        when(mapper.toResponse(mockOrderContent.get(0))).thenReturn(exptectedResponse.get(0));
        when(mapper.toResponse(mockOrderContent.get(1))).thenReturn(exptectedResponse.get(1));

        // --- 2. EYLEM (Act) ---

        List<OrderSummaryResponse> actualResponse = handler.handle(query).content();


        // --- 3. DOĞRULAMA (Assert) ---

        // a) Çıktı Doğrulama
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse).isEqualTo(exptectedResponse);

        // b) Davranış Doğrulama

        // 1. Repository Kontrolü (Aynı kalır)
        ArgumentCaptor<Integer> pageIndexCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> pageSizeCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(orderRepository, times(1)).findAllPaged(
                pageIndexCaptor.capture(),
                pageSizeCaptor.capture()
        );

        // 2. Mapper Kontrolü (Her bir Order için bir kez çağrıldı mı?)
        // toResponse metodu, toplamda 2 kez (mockOrderContent'teki eleman sayısı kadar) çağrılmalıdır.
        verify(mapper, times(1)).toResponse(mockOrderContent.get(0));
        verify(mapper, times(1)).toResponse(mockOrderContent.get(1));



    }


}

package com.infonal.orderservice.application.query;

import com.infonal.orderservice.application.dto.OrderSummaryResponse;
import com.infonal.orderservice.application.dto.PagedResponse;
import com.infonal.orderservice.application.mapper.OrderSummaryResponseMapper;
import com.infonal.orderservice.cqrs.QueryHandler;
import com.infonal.orderservice.domain.model.Order;
import com.infonal.orderservice.domain.port.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FindAllOrdersQueryHandler implements QueryHandler<FindAllOrdersQuery, PagedResponse<OrderSummaryResponse>> {
    private final OrderRepository orderRepository;
    private final OrderSummaryResponseMapper mapper;

    public FindAllOrdersQueryHandler(OrderRepository orderRepository, OrderSummaryResponseMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public PagedResponse<OrderSummaryResponse> handle(FindAllOrdersQuery query) {
        // toplam kayıt sayısı
        long totalElements = orderRepository.countAll();

        // sayfalanmıs veriyi çekme
        List<Order> pagedOrders = orderRepository.findAllPaged(query.pageIndex(), query.pageSize());

        // domain response dönüşümü
        List<OrderSummaryResponse> responseContent = pagedOrders.stream().map(mapper::toResponse).toList();

        // paged response olusturma
        // Hesaplama: Toplam Sayfa = (Toplam Kayıt / Sayfa Büyüklüğü) Yuvarlama
        int totalPages = (int) Math.ceil((double)  totalElements / query.pageSize());

        return new PagedResponse<>(
                responseContent,
                query.pageIndex(),
                query.pageSize(),
                totalElements,
                totalPages
                );
    }
}

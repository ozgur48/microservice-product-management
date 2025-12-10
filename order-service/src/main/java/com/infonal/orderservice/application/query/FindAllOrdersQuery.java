package com.infonal.orderservice.application.query;

import com.infonal.orderservice.application.dto.OrderSummaryResponse;
import com.infonal.orderservice.application.dto.PagedResponse;
import com.infonal.orderservice.cqrs.Query;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;


public record FindAllOrdersQuery(
        @Min(value = 0, message="Page index must be bigger than 0") int pageIndex,
        @Positive @Max(value = 100, message = "Page number can be max 100") int pageSize
) implements Query<PagedResponse<OrderSummaryResponse>> {
}

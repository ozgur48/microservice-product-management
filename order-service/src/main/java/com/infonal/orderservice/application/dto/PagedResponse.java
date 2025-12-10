package com.infonal.orderservice.application.dto;

import java.util.List;

public record PagedResponse<T>(
        List<T> content,
        int pageIndex,
        int pageSize,
        long totalElements,
        int totalPages
) {
}

package com.infonal.orderservice.application.dto;

import java.util.UUID;

public record UpdatedOrderAddressResponse(UUID orderId , String city, String district) {
}

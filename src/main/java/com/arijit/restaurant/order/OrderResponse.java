package com.arijit.restaurant.order;

import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderResponse(
        UUID id,
        Double totalPrice,
        OrderStatus orderStatus
) {
}

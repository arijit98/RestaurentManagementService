package com.arijit.restaurant.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Builder
public record Order(
        UUID id,
        Integer tableId,
        Integer customerId,
        String customerName,
        OrderStatus status,
        Double totalPrice,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<OrderItem> items
) {
}

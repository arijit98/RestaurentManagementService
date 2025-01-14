package com.arijit.restaurant.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Order(
        UUID id,
        Integer tableId,
        String customerName,
        OrderStatus status,
        Double totalPrice,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<OrderItem> items
) {
}

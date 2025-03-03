package com.arijit.restaurant.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Data
@Builder
public class Order {
    private UUID id;
    private Integer tableId;
    private String customerName;
    private OrderStatus status;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderItem> items;
}

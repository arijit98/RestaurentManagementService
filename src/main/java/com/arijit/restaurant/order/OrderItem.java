package com.arijit.restaurant.order;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderItem {
    private UUID menuItemId;
    private UUID orderId;
    private Integer quantity;
}

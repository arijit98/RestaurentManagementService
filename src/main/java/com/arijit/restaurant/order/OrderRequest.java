package com.arijit.restaurant.order;

import java.util.List;
import java.util.UUID;

public record OrderRequest(
        List<UUID> menuItems,
        Integer tableId
) {
}

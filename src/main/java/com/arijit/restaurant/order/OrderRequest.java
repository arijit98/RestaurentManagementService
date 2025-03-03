package com.arijit.restaurant.order;

import org.jooq.impl.QOM;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record OrderRequest(
        Map<UUID, Integer> menuItems,
        Integer tableId
) {
}

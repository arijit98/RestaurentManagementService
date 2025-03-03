package com.arijit.restaurant.order;

import lombok.RequiredArgsConstructor;
import org.jooq.impl.QOM;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping("/{tableId}")
    public Order getOrdersForTable(@PathVariable Integer tableId) {
        return orderService.getOrderFor(tableId);
    }

    @PostMapping
    public void addOrder(@RequestBody OrderRequest request) {
        Order order = Order.builder()
                .tableId(request.tableId())
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        orderService.addOrder(order, request.menuItems());
    }

    @PutMapping("/id")
    public void updateOrder(@RequestParam UUID id, @RequestBody Order order) {

    }
}

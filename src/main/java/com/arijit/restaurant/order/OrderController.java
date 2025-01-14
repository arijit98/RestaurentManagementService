package com.arijit.restaurant.order;

import lombok.RequiredArgsConstructor;
import org.jooq.impl.QOM;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

//    POST /api/orders: Place a new order.
//            GET /api/orders/{tableId}: Get all orders for a table.
//    PUT /api/orders/{id}: Update the status of an order.

    private OrderService orderService;

    @GetMapping("/{tableId}")
    public Order getOrdersForTable(@PathVariable Integer tableId) {
        return orderService.getOrderFor(tableId);
    }

    @PostMapping
    public void addOrder(@RequestBody OrderRequest request) {
        orderService.addOrder();
    }

    @PutMapping("/id")
    public void updateOrder(@RequestParam UUID id, @RequestBody Order order) {

    }
}

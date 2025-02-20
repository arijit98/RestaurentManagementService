package com.arijit.restaurant.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public Order placeOrder(@RequestBody OrderRequest request) {
        return service.placeOrder(request.customerId(),request.tableId(),  request.menuItems());
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable UUID orderId) {
        return service.getOrder(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @PatchMapping("/{orderId}/status")
    public void updateOrderStatus(@PathVariable UUID orderId, @RequestBody OrderStatus status) {
         service.updateOrderStatus(orderId, status);
    }

    @GetMapping("/{tableId}")
    public Order getOrdersForTable(@PathVariable Integer tableId) {
        return service.getOrderFor(tableId);
    }

    @PutMapping("/id")
    public void updateOrder(@RequestParam UUID id, @RequestBody Order order) {

    }
}

package com.arijit.restaurant.order;

import com.arijit.restaurant.menu.MenuItem;
import com.arijit.restaurant.menu.MenuService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private MenuService menuService;

    public Order placeOrder(Integer tableId, Integer customerId, List<UUID> items) {

        Double totalAmount = calculateTotalAmount(items);

        Order order = Order.builder()
                .id(UUID.randomUUID())
                .customerId(customerId)
                .tableId(tableId)
                .totalPrice(totalAmount)
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

         orderRepository.insert(order);
         return order;
    }

    public Optional<Order> getOrder(UUID orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void updateOrderStatus(UUID orderId, OrderStatus status) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.update(order.id(), status);
    }
    public Order getOrderFor(Integer tableId) {
        return orderRepository.getOrderFor(tableId);
    }

    private double calculateTotalAmount(List<UUID> itemIds){
        return menuService.getMenuDetails(itemIds).stream().mapToDouble(MenuItem::getItemPrice).sum();
    }
}

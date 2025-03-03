package com.arijit.restaurant.order;

import com.arijit.restaurant.menu.MenuItem;
import com.arijit.restaurant.menu.MenuService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class OrderService {
    private MenuService menuService;
    private OrderRepository orderRepository;

    public void addOrder(Order order, Map<UUID, Integer> orderItems) {

        List<MenuItem> menuItems = menuService.getMenuItemsByIds(orderItems.keySet());
        order.setTotalPrice(calculatePrice(menuItems, orderItems));
        UUID orderId = orderRepository.insert(order);
        orderRepository.insertOrderItems(menuItems,orderId);
    }

    private double calculatePrice(List<MenuItem> menuItems, Map<UUID, Integer> orderItems) {
        double sum = 0;
        for (Map.Entry<UUID, Integer> entry : orderItems.entrySet()) {
            UUID id = entry.getKey();
            int qty = entry.getValue();
            MenuItem menuItem = menuItems.stream()
                    .filter(item -> item.getItemId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Menu item not found for ID: " + id));
            sum += menuItem.getItemPrice() * qty;
        }
        return sum;
    }

    public Order getOrderFor(Integer tableId) {
        return orderRepository.getOrderFor(tableId);
    }
}

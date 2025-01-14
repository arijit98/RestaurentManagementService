package com.arijit.restaurant.order;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    public void addOrder() {

//        orderRepository.insert(new Order());
    }

    public Order getOrderFor(Integer tableId) {
        return orderRepository.getOrderFor(tableId);
    }
}

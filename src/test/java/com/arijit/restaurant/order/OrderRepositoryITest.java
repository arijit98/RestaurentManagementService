package com.arijit.restaurant.order;

import com.arijit.restaurant.menu.MenuItem;
import com.arijit.restaurant.menu.MenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@SqlGroup({
        @Sql(scripts = "classpath:sql/order_insertions.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:sql/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class OrderRepositoryITest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findAllOrdersForTable_retrievesPendingOrder() {
        // Given
        Integer tableId = 1;

        // When
        Order order = orderRepository.getOrderFor(tableId);

        // Then
        assertNotNull(order);
        assertEquals(tableId, order.getTableId());
        assertEquals("PENDING", order.getStatus().name());
    }

    @Test
    public void insert_savesOrderSuccessfully() {
        // Given
        Order order = Order.builder()
                .id(UUID.randomUUID())
                .tableId(1)
                .customerName("Test Customer")
                .status(OrderStatus.PENDING)
                .totalPrice(100.0)
                .build();

        // When
        orderRepository.insert(order);

        // Then
        Order retrievedOrder = orderRepository.getOrderFor(order.getTableId());
        assertNotNull(retrievedOrder);
        assertEquals(order.getTableId(), retrievedOrder.getTableId());
        assertEquals(order.getCustomerName(), retrievedOrder.getCustomerName());
        assertEquals(order.getStatus(), retrievedOrder.getStatus());
        assertEquals(order.getTotalPrice(), retrievedOrder.getTotalPrice());
    }
}

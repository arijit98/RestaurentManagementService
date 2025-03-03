package com.arijit.restaurant.order;

import com.arijit.restaurant.menu.MenuItem;
import com.arijit.restaurant.tables.records.OrderItemsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.InsertSetStep;
import org.jooq.TableRecord;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static com.arijit.restaurant.Tables.ORDERS;
import static com.arijit.restaurant.Tables.ORDER_ITEMS;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private DSLContext dslContext;

    public Order getOrderFor(Integer tableId) {
        return dslContext.selectFrom(ORDERS)
                .where(ORDERS.TABLE_ID.eq(tableId).and(ORDERS.STATUS.eq("PENDING")))
                .fetchOneInto(Order.class);
    }


    public UUID insert(Order order) {
        UUID orderId = UUID.randomUUID();
        dslContext.insertInto(ORDERS)
                .set(ORDERS.ID, orderId)
                .set(ORDERS.TABLE_ID, order.getTableId())
                .set(ORDERS.CUSTOMER_NAME, order.getCustomerName())
                .set(ORDERS.STATUS, order.getStatus().name())
                .set(ORDERS.TOTAL_PRICE, order.getTotalPrice())
                .execute();
        return orderId;

    }

    public void insertOrderItems(Collection<MenuItem> orderItems, UUID orderId) {

        List<InsertSetMoreStep<OrderItemsRecord>> inserts = orderItems
                .stream()
                .map(orderItem -> insertOrderItem(orderItem, orderId))
                .toList();
        dslContext.batch(inserts).execute();
    }

    private InsertSetMoreStep<OrderItemsRecord> insertOrderItem(MenuItem orderItem, UUID orderId) {
        return dslContext.insertInto(ORDER_ITEMS)
                .set(ORDER_ITEMS.ORDER_ID, orderId)
                .set(ORDER_ITEMS.MENU_ID, orderItem.getItemId());
    }
}

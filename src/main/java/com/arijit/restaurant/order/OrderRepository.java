package com.arijit.restaurant.order;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.arijit.restaurant.Tables.ORDERS;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private DSLContext dslContext;

    public Order getOrderFor(Integer tableId) {
        return dslContext.selectFrom(ORDERS)
                .where(ORDERS.TABLE_ID.eq(tableId).and(ORDERS.STATUS.eq("PENDING")))
                .fetchOneInto(Order.class);
    }


    public void insert(Order order) {
        dslContext.insertInto(ORDERS)
                .set(ORDERS.ID, UUID.randomUUID())
                .set(ORDERS.TABLE_ID, order.tableId())
                .set(ORDERS.CUSTOMER_NAME, order.customerName())
                .set(ORDERS.STATUS, order.status().name())
                .set(ORDERS.TOTAL_PRICE, order.totalPrice())
                .execute();

    }
}

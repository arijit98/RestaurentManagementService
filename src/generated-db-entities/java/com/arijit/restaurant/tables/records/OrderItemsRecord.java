/*
 * This file is generated by jOOQ.
 */
package com.arijit.restaurant.tables.records;


import com.arijit.restaurant.tables.OrderItems;

import java.beans.ConstructorProperties;
import java.util.UUID;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrderItemsRecord extends TableRecordImpl<OrderItemsRecord> implements Record3<UUID, UUID, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.order_items.order_id</code>.
     */
    public OrderItemsRecord setOrderId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.order_items.order_id</code>.
     */
    public UUID getOrderId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.order_items.menu_id</code>.
     */
    public OrderItemsRecord setMenuId(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.order_items.menu_id</code>.
     */
    public UUID getMenuId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.order_items.quantity</code>.
     */
    public OrderItemsRecord setQuantity(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.order_items.quantity</code>.
     */
    public Integer getQuantity() {
        return (Integer) get(2);
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, UUID, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<UUID, UUID, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return OrderItems.ORDER_ITEMS.ORDER_ID;
    }

    @Override
    public Field<UUID> field2() {
        return OrderItems.ORDER_ITEMS.MENU_ID;
    }

    @Override
    public Field<Integer> field3() {
        return OrderItems.ORDER_ITEMS.QUANTITY;
    }

    @Override
    public UUID component1() {
        return getOrderId();
    }

    @Override
    public UUID component2() {
        return getMenuId();
    }

    @Override
    public Integer component3() {
        return getQuantity();
    }

    @Override
    public UUID value1() {
        return getOrderId();
    }

    @Override
    public UUID value2() {
        return getMenuId();
    }

    @Override
    public Integer value3() {
        return getQuantity();
    }

    @Override
    public OrderItemsRecord value1(UUID value) {
        setOrderId(value);
        return this;
    }

    @Override
    public OrderItemsRecord value2(UUID value) {
        setMenuId(value);
        return this;
    }

    @Override
    public OrderItemsRecord value3(Integer value) {
        setQuantity(value);
        return this;
    }

    @Override
    public OrderItemsRecord values(UUID value1, UUID value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OrderItemsRecord
     */
    public OrderItemsRecord() {
        super(OrderItems.ORDER_ITEMS);
    }

    /**
     * Create a detached, initialised OrderItemsRecord
     */
    @ConstructorProperties({ "orderId", "menuId", "quantity" })
    public OrderItemsRecord(UUID orderId, UUID menuId, Integer quantity) {
        super(OrderItems.ORDER_ITEMS);

        setOrderId(orderId);
        setMenuId(menuId);
        setQuantity(quantity);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised OrderItemsRecord
     */
    public OrderItemsRecord(com.arijit.restaurant.tables.pojos.OrderItems value) {
        super(OrderItems.ORDER_ITEMS);

        if (value != null) {
            setOrderId(value.getOrderId());
            setMenuId(value.getMenuId());
            setQuantity(value.getQuantity());
            resetChangedOnNotNull();
        }
    }
}

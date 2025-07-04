/*
 * This file is generated by jOOQ.
 */
package com.arijit.restaurant;


import com.arijit.restaurant.tables.Menu;
import com.arijit.restaurant.tables.OrderItems;
import com.arijit.restaurant.tables.Orders;
import com.arijit.restaurant.tables.Restaurant;
import com.arijit.restaurant.tables.Users;

import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.menu</code>.
     */
    public final Menu MENU = Menu.MENU;

    /**
     * The table <code>public.order_items</code>.
     */
    public final OrderItems ORDER_ITEMS = OrderItems.ORDER_ITEMS;

    /**
     * The table <code>public.orders</code>.
     */
    public final Orders ORDERS = Orders.ORDERS;

    /**
     * The table <code>public.restaurant</code>.
     */
    public final Restaurant RESTAURANT = Restaurant.RESTAURANT;

    /**
     * The table <code>public.users</code>.
     */
    public final Users USERS = Users.USERS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Menu.MENU,
            OrderItems.ORDER_ITEMS,
            Orders.ORDERS,
            Restaurant.RESTAURANT,
            Users.USERS
        );
    }
}

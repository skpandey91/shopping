package com.zooplus.shoppingcart.Entity;

import com.zooplus.shoppingcart.enitity.Orders;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderTest {

    @Test
    public void testConstructors() {
        Orders orders = new Orders();
        assertNull(orders.getOrderId());
        assertNull(orders.getCustomerId());
        assertNull(orders.getAmount());

        orders = new Orders(10L, 10L, 20.5);
        assertEquals(10L, orders.getOrderId());
        assertEquals(10L, orders.getCustomerId());
        assertEquals(20.5, orders.getAmount());

    }

    @Test
    public void testToString() {

    }
}
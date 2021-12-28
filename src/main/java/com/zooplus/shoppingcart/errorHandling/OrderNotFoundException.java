package com.zooplus.shoppingcart.errorHandling;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String order_id_is_not_valid) {
        super(order_id_is_not_valid);
    }
}
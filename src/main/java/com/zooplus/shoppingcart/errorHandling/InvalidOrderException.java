package com.zooplus.shoppingcart.errorHandling;

public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException(String order_id_is_not_valid) {
        super(order_id_is_not_valid);
    }
}
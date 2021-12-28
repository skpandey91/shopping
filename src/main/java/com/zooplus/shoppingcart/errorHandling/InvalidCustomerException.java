package com.zooplus.shoppingcart.errorHandling;

public class InvalidCustomerException extends RuntimeException {
    public InvalidCustomerException(String customer_id_is_not_valid) {
        super(customer_id_is_not_valid);
    }
}
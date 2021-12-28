package com.zooplus.shoppingcart.errorHandling;

public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String payment_id_is_not_valid) {
        super(payment_id_is_not_valid);
    }
}
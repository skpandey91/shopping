package com.zooplus.shoppingcart.service.interfaces;

import com.zooplus.shoppingcart.enitity.Payment;

public interface CreatePaymentService {
    public Payment savePayment(Payment payment);
}
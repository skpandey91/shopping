package com.zooplus.shoppingcart.repository.interfaces;

import com.zooplus.shoppingcart.enitity.Payment;

import java.util.List;


public interface PaymentRepository {

    public List<Payment> getPaymentsByOrderId(long id);

    public Payment save(Payment payment);

}
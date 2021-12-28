package com.zooplus.shoppingcart.repository.implementations;

import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.repository.interfaces.PaymentJpaRepository;
import com.zooplus.shoppingcart.repository.interfaces.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {

    @Autowired
    private PaymentJpaRepository paymentJpaRepository;

    @Override
    public List<Payment> getPaymentsByOrderId(long id) {
        return paymentJpaRepository.getPaymentsByOrderId(id);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentJpaRepository.save(payment);
    }
}
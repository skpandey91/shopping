package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.repository.interfaces.PaymentRepository;
import com.zooplus.shoppingcart.service.interfaces.CreatePaymentService;
import com.zooplus.shoppingcart.service.interfaces.GetOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CreatePaymentServiceImpl implements CreatePaymentService {
    @Autowired
    private PaymentRepository paymentRepositoryImpl;
    @Autowired
    private GetOrderService getOrderServiceImpl;

    public Payment savePayment(Payment payment) {
        log.info("saving payment with order id:", payment.getOrderId());
        getOrderServiceImpl.findById((payment.getOrderId()));
        return paymentRepositoryImpl.save(payment);
    }
}
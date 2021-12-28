package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.repository.interfaces.PaymentRepository;
import com.zooplus.shoppingcart.service.interfaces.GetPaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GetPaymentServiceImpl implements GetPaymentService {
    @Autowired
    private PaymentRepository paymentRepositoryImpl;

    public List<Payment> getPaymentByOrderId(Long id) {
        return paymentRepositoryImpl.getPaymentsByOrderId(id);

    }

}

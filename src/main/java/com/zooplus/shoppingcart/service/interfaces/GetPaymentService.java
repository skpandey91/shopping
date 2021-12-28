package com.zooplus.shoppingcart.service.interfaces;

import com.zooplus.shoppingcart.enitity.Payment;

import java.util.List;

public interface GetPaymentService {
    public List<Payment> getPaymentByOrderId(Long id);

}

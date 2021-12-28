package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.errorHandling.InvalidOrderException;
import com.zooplus.shoppingcart.repository.interfaces.OrderRepository;
import com.zooplus.shoppingcart.service.interfaces.GetPaymentService;
import com.zooplus.shoppingcart.service.interfaces.OrderBalanceService;
import com.zooplus.shoppingcart.vo.OrderBalance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderBalanceServiceImpl implements OrderBalanceService {
    @Autowired
    private OrderRepository orderRepositoryImpl;
    @Autowired
    private GetPaymentService getPaymentServiceImpl;

    public OrderBalance getOrderBalanceById(Long id) {

        Orders orders;
        orders = orderRepositoryImpl.findById(id).orElseThrow(() -> new InvalidOrderException(String.format("Order Id %d is not valid", id)));
        List<Payment> payments = getPaymentServiceImpl.getPaymentByOrderId(id);
        Double paidAmount = payments.stream().mapToDouble(p -> p.getAmount()).sum();
        return new OrderBalance(id, orders.getAmount() - paidAmount);

    }

}
package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.errorHandling.InvalidCustomerException;
import com.zooplus.shoppingcart.service.interfaces.GetCustomerBalanceService;
import com.zooplus.shoppingcart.service.interfaces.GetOrderService;
import com.zooplus.shoppingcart.service.interfaces.GetPaymentService;
import com.zooplus.shoppingcart.service.interfaces.ValidateCustomerService;
import com.zooplus.shoppingcart.vo.CustomerBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class GetCustomerBalanceServiceImpl implements GetCustomerBalanceService {
    @Autowired
    private GetOrderService getOrderServiceImpl;
    @Autowired
    private ValidateCustomerService validateCustomerServiceImpl;
    @Autowired
    private GetPaymentService getPaymentServiceImpl;

    public CustomerBalance getCustomerBalance(Long id) {
        if (!validateCustomerServiceImpl.validateCustomerId(id)) {
            log.debug("customer id is not valid id: ", id);
            throw new InvalidCustomerException(String.format("Customer id %s is not valid", id));
        }
        List<Orders> orders = getOrderServiceImpl.getOrdersByCustomerId(id);
        Double orderAmount = orders.stream().mapToDouble(o -> o.getAmount()).sum();
        Double paymentDone = orders.stream().mapToDouble
                (o -> getPaymentServiceImpl
                        .getPaymentByOrderId(o.getOrderId())
                        .stream().mapToDouble(p -> p.getAmount()).sum()).sum();
        return new CustomerBalance(id, orderAmount - paymentDone);
    }
}
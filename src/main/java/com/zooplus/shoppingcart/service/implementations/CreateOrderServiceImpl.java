package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.errorHandling.InvalidCustomerException;
import com.zooplus.shoppingcart.repository.interfaces.OrderRepository;
import com.zooplus.shoppingcart.service.interfaces.CreateOrderService;
import com.zooplus.shoppingcart.service.interfaces.ValidateCustomerService;
import com.zooplus.shoppingcart.service.interfaces.ValidateOrderIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CreateOrderServiceImpl implements CreateOrderService {
    @Autowired
    private OrderRepository orderRepositoryImpl;
    @Autowired
    private ValidateCustomerService validateCustomerServiceImpl;
    @Autowired
    private ValidateOrderIdService validateOrderIdServiceImpl;

    public Orders save(Orders orders) {
        boolean custExists = validateCustomerServiceImpl.validateCustomerId(orders.getCustomerId());
        if (!custExists) {
            log.debug("Customer does not exist");
            throw new InvalidCustomerException(String.format("Customer Id %s is not valid", orders.getCustomerId()));
        }
        if (validateOrderIdServiceImpl.validateOrderId(orders.getOrderId())) {
            log.debug("Order is already exist with ID: " + orders.getOrderId());
            throw new IllegalArgumentException("Order id already exists");
        }
        log.info("saving order", orders);
        return orderRepositoryImpl.save(orders);
    }
}
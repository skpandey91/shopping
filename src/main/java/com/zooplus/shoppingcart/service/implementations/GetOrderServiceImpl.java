package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.errorHandling.InvalidOrderException;
import com.zooplus.shoppingcart.repository.interfaces.OrderRepository;
import com.zooplus.shoppingcart.service.interfaces.GetOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GetOrderServiceImpl implements GetOrderService {

    @Autowired
    private OrderRepository orderRepositoryImpl;

    public Orders findById(Long id) {
        return orderRepositoryImpl.findById(id)
                .orElseThrow(() -> new InvalidOrderException(String.format("Order id %s is not valid", id)));
    }

    public List<Orders> getOrdersByCustomerId(Long id) {
        return orderRepositoryImpl.getOrdersByCustomerId(id);
    }
}
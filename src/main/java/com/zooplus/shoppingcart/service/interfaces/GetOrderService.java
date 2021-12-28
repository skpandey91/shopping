package com.zooplus.shoppingcart.service.interfaces;

import com.zooplus.shoppingcart.enitity.Orders;

import java.util.List;

public interface GetOrderService {
    public Orders findById(Long id);

    public List<Orders> getOrdersByCustomerId(Long id);
}
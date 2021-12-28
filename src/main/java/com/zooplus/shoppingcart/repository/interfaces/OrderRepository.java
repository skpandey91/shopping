package com.zooplus.shoppingcart.repository.interfaces;

import com.zooplus.shoppingcart.enitity.Orders;

import java.util.List;
import java.util.Optional;


public interface OrderRepository {
    public List<Orders> getOrdersByCustomerId(long id);

    public Orders save(Orders orders);

    public Optional<Orders> findById(Long id);
}
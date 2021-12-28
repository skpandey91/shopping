package com.zooplus.shoppingcart.repository.implementations;

import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.repository.interfaces.OrderJpaRepository;
import com.zooplus.shoppingcart.repository.interfaces.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Override
    public List<Orders> getOrdersByCustomerId(long id) {
        return orderJpaRepository.getOrdersByCustomerId(id);
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return orderJpaRepository.findById(id);
    }

    @Override
    public Orders save(Orders orders) {
        return orderJpaRepository.save(orders);
    }
}
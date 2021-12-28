package com.zooplus.shoppingcart.repository.implementations;

import com.zooplus.shoppingcart.repository.interfaces.OrderJpaRepository;
import com.zooplus.shoppingcart.repository.interfaces.ValidateOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateOrderRepositoryImpl implements ValidateOrderRepository {
    @Autowired
    private OrderJpaRepository orderJpaRepository;

    public boolean validateOrderId(Long id) {
        return orderJpaRepository.existsById(id);
    }
}
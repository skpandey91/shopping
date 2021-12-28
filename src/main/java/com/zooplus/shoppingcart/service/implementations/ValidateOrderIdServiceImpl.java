package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.repository.interfaces.ValidateOrderRepository;
import com.zooplus.shoppingcart.service.interfaces.ValidateOrderIdService;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateOrderIdServiceImpl implements ValidateOrderIdService {
    @Autowired
    private ValidateOrderRepository validateOrderRepositoryImpl;

    @Override
    public boolean validateOrderId(Long id) {
        return validateOrderRepositoryImpl.validateOrderId(id);
    }
}
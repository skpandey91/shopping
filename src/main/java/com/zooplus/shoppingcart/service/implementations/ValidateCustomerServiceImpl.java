package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.repository.interfaces.CustomerRepository;
import com.zooplus.shoppingcart.service.interfaces.ValidateCustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateCustomerServiceImpl implements ValidateCustomerService {
    @Autowired
    private CustomerRepository customerRepositoryimpl;

    public boolean validateCustomerId(Long id) {
        return customerRepositoryimpl.existsById(id);
    }

}
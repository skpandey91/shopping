package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Customer;
import com.zooplus.shoppingcart.repository.interfaces.CustomerRepository;
import com.zooplus.shoppingcart.service.interfaces.CreateCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CreateCustomerServiceImpl implements CreateCustomerService {

    @Autowired
    private CustomerRepository customerRepositoryImpl;

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("saving customer", customer);
        return customerRepositoryImpl.save(customer);
    }
}
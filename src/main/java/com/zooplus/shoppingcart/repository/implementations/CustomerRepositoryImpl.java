package com.zooplus.shoppingcart.repository.implementations;

import com.zooplus.shoppingcart.enitity.Customer;
import com.zooplus.shoppingcart.repository.interfaces.CustomerJpaRepository;
import com.zooplus.shoppingcart.repository.interfaces.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Override
    public Customer save(Customer customer) {
        return customerJpaRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerJpaRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return customerJpaRepository.existsById(id);
    }
}
package com.zooplus.shoppingcart.repository.interfaces;

import com.zooplus.shoppingcart.enitity.Customer;

import java.util.Optional;


public interface CustomerRepository {
    public Customer save(Customer customer);

    public Optional<Customer> findById(Long id);

    public boolean existsById(Long id);
}
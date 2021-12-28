package com.zooplus.shoppingcart.repository.interfaces;

import com.zooplus.shoppingcart.enitity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
}
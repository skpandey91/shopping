package com.zooplus.shoppingcart.config;

import com.zooplus.shoppingcart.repository.implementations.CustomerRepositoryImpl;
import com.zooplus.shoppingcart.repository.implementations.OrderRepositoryImpl;
import com.zooplus.shoppingcart.repository.implementations.PaymentRepositoryImpl;
import com.zooplus.shoppingcart.repository.implementations.ValidateOrderRepositoryImpl;
import com.zooplus.shoppingcart.repository.interfaces.CustomerRepository;
import com.zooplus.shoppingcart.repository.interfaces.OrderRepository;
import com.zooplus.shoppingcart.repository.interfaces.PaymentRepository;
import com.zooplus.shoppingcart.repository.interfaces.ValidateOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public CustomerRepository customerRepositoryImpl() {
        return new CustomerRepositoryImpl();
    }

    @Bean
    public OrderRepository orderRepositoryImpl() {
        return new OrderRepositoryImpl();
    }

    @Bean
    public PaymentRepository paymentRepositoryImpl() {
        return new PaymentRepositoryImpl();
    }

    @Bean
    public ValidateOrderRepository validateOrderRepositoryImpl() {
        return new ValidateOrderRepositoryImpl();
    }
}
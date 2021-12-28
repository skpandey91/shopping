package com.zooplus.shoppingcart.config;

import com.zooplus.shoppingcart.service.implementations.CreateCustomerServiceImpl;
import com.zooplus.shoppingcart.service.implementations.CreateOrderServiceImpl;
import com.zooplus.shoppingcart.service.implementations.CreatePaymentServiceImpl;
import com.zooplus.shoppingcart.service.implementations.GetCustomerBalanceServiceImpl;
import com.zooplus.shoppingcart.service.implementations.GetOrderServiceImpl;
import com.zooplus.shoppingcart.service.implementations.GetPaymentServiceImpl;
import com.zooplus.shoppingcart.service.implementations.OrderBalanceServiceImpl;
import com.zooplus.shoppingcart.service.implementations.ValidateCustomerServiceImpl;
import com.zooplus.shoppingcart.service.implementations.ValidateOrderIdServiceImpl;
import com.zooplus.shoppingcart.service.interfaces.CreateCustomerService;
import com.zooplus.shoppingcart.service.interfaces.CreateOrderService;
import com.zooplus.shoppingcart.service.interfaces.CreatePaymentService;
import com.zooplus.shoppingcart.service.interfaces.GetCustomerBalanceService;
import com.zooplus.shoppingcart.service.interfaces.GetOrderService;
import com.zooplus.shoppingcart.service.interfaces.GetPaymentService;
import com.zooplus.shoppingcart.service.interfaces.OrderBalanceService;
import com.zooplus.shoppingcart.service.interfaces.ValidateCustomerService;
import com.zooplus.shoppingcart.service.interfaces.ValidateOrderIdService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public CreateCustomerService createCustomerServiceImpl() {
        return new CreateCustomerServiceImpl();
    }

    @Bean
    public CreateOrderService createOrderServiceImpl() {
        return new CreateOrderServiceImpl();
    }

    @Bean
    public CreatePaymentService createPaymentServiceImpl() {
        return new CreatePaymentServiceImpl();
    }

    @Bean
    public GetCustomerBalanceService getCustomerBalanceServiceImpl() {
        return new GetCustomerBalanceServiceImpl();
    }

    @Bean
    public GetOrderService gerOrderServiceImpl() {
        return new GetOrderServiceImpl();
    }

    @Bean
    public GetPaymentService GetPaymentServiceImpl() {
        return new GetPaymentServiceImpl();
    }

    @Bean
    public OrderBalanceService orderBalanceServiceImpl() {
        return new OrderBalanceServiceImpl();
    }

    @Bean
    public ValidateCustomerService validateCustomerServiceImpl() {
        return new ValidateCustomerServiceImpl();
    }

    @Bean
    public ValidateOrderIdService validateOrderService() {
        return new ValidateOrderIdServiceImpl();
    }
}
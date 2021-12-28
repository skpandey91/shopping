package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.repository.interfaces.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateCustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepositoryImpl;
    @InjectMocks
    private ValidateCustomerServiceImpl validateCustomerServiceImpl;
    private Orders orders = new Orders(1L, 1L, 20.00);

    @Test
    public void testValidateOrderId() {
        when(customerRepositoryImpl.existsById(orders.getCustomerId())).thenReturn(true);
        assertTrue(validateCustomerServiceImpl.validateCustomerId(orders.getOrderId()));
    }

    @Test
    public void testValidateOrderIdNegative() {
        when(customerRepositoryImpl.existsById(orders.getOrderId())).thenReturn(false);
        assertFalse(validateCustomerServiceImpl.validateCustomerId(orders.getOrderId()));
    }

}
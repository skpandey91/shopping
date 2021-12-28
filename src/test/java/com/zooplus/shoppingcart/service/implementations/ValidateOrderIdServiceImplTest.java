package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.repository.interfaces.ValidateOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ValidateOrderIdServiceImplTest {
    @Mock
    private ValidateOrderRepository validateOrderRepositoryImpl;
    @InjectMocks
    private ValidateOrderIdServiceImpl validateOrderIdServiceImpl;
    private Orders orders = new Orders(1L, 1L, 20.00);

    @Test
    public void testValidateOrderId() {
        when(validateOrderRepositoryImpl.validateOrderId(orders.getOrderId())).thenReturn(true);
        assertTrue(validateOrderIdServiceImpl.validateOrderId(orders.getOrderId()));
    }

    @Test
    public void testValidateOrderIdNegative() {
        when(validateOrderRepositoryImpl.validateOrderId(orders.getOrderId())).thenReturn(false);
        assertFalse(validateOrderIdServiceImpl.validateOrderId(orders.getOrderId()));
    }

}
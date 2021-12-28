package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.errorHandling.InvalidCustomerException;
import com.zooplus.shoppingcart.repository.interfaces.OrderRepository;
import com.zooplus.shoppingcart.service.interfaces.ValidateCustomerService;
import com.zooplus.shoppingcart.service.interfaces.ValidateOrderIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderServiceImplTest {
    @Mock
    private OrderRepository orderRepositoryImpl;
    @Mock
    private ValidateCustomerService validateCustomerServiceImpl;
    @Mock
    private ValidateOrderIdService validateOrderIdServiceImpl;
    @InjectMocks
    private CreateOrderServiceImpl createOrderServiceImpl;

    private Orders order = new Orders(1L, 1L, 10.00);

    @Test
    public void testSaveOrder() {
        when(validateCustomerServiceImpl.validateCustomerId(order.getCustomerId())).thenReturn(true);
        when(validateOrderIdServiceImpl.validateOrderId(order.getOrderId())).thenReturn(false);
        when(orderRepositoryImpl.save(order)).thenReturn(order);
        Orders orders = createOrderServiceImpl.save(order);
        verify(orderRepositoryImpl).save(order);
        assertEquals(1, orders.getCustomerId());

    }

    @Test
    public void testSaveOrderIfCustomerNotExists() {
        when(validateCustomerServiceImpl.validateCustomerId(order.getCustomerId())).thenReturn(false);
        assertThrows(InvalidCustomerException.class, () -> createOrderServiceImpl.save(order));
    }

    @Test
    public void testSaveOrderIfOrderExists() {
        when(validateCustomerServiceImpl.validateCustomerId(order.getCustomerId())).thenReturn(false);
        assertThrows(InvalidCustomerException.class, () -> createOrderServiceImpl.save(order));
    }
}
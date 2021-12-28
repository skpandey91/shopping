package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Customer;
import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.errorHandling.InvalidOrderException;
import com.zooplus.shoppingcart.repository.interfaces.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderServiceImplTest {
    @Mock
    private OrderRepository orderRepositoryImpl;
    @InjectMocks
    private GetOrderServiceImpl getOrderServiceImpl;

    private Payment payment = new Payment(1L, 1L, 10.00);
    private Orders orders = new Orders(1L, 1L, 20.00);
    private Customer customer = new Customer(1L, "Sud", "Pan");

    @Test
    public void testFindById() {
        when(orderRepositoryImpl.findById(orders.getOrderId())).thenReturn(Optional.of(orders));
        Orders orders1 = getOrderServiceImpl.findById(orders.getOrderId());
        verify(orderRepositoryImpl).findById(orders.getOrderId());
        assertEquals(1, orders1.getOrderId());

    }

    @Test
    public void testFindByIdIfOrderNotExist() {
        when(orderRepositoryImpl.findById(orders.getOrderId())).thenReturn(Optional.empty());
        assertThrows(InvalidOrderException.class, () -> getOrderServiceImpl.findById(orders.getOrderId()));

    }

    @Test
    public void testGetOrdersByCustomerId() {
        when(orderRepositoryImpl.getOrdersByCustomerId(orders.getCustomerId())).thenReturn(List.of(orders));
        List<Orders> ordersList = getOrderServiceImpl.getOrdersByCustomerId(orders.getCustomerId());
        verify(orderRepositoryImpl).getOrdersByCustomerId(orders.getCustomerId());
        assertEquals(List.of(orders), ordersList);
    }
}
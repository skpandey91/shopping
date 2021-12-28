package com.zooplus.shoppingcart.cntroller;

import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.errorHandling.InvalidOrderException;
import com.zooplus.shoppingcart.service.interfaces.CreateOrderService;
import com.zooplus.shoppingcart.service.interfaces.GetOrderService;
import com.zooplus.shoppingcart.service.interfaces.OrderBalanceService;
import com.zooplus.shoppingcart.vo.OrderBalance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @InjectMocks
    private OrderController orderController;
    @Mock
    private CreateOrderService createOrderServiceImpl;
    @Mock
    private GetOrderService getOrderServiceImpl;
    @Mock
    private OrderBalanceService orderBalanceServiceImpl;

    @Test
    public void checkoutPositive() {
        Orders orders = new Orders(1L, 2L, 10.0);
        when(createOrderServiceImpl.save(orders)).thenReturn(orders);
        ResponseEntity<Orders> response = orderController.checkout(orders);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, response.getBody().getOrderId());
        verify(createOrderServiceImpl).save(orders);
    }

    @Test
    public void checkoutThrowsException() {
        Orders orders = new Orders(1L, 2L, 10.0);
        when(createOrderServiceImpl.save(orders)).thenThrow(new IllegalArgumentException("Customer already exists"));
        assertThrows(IllegalArgumentException.class, () -> orderController.checkout(orders));

    }

    @Test
    public void getOrderPositive() {
        Orders orders = new Orders(1L, 2L, 10.0);
        when(getOrderServiceImpl.findById(1L)).thenReturn(orders);
        ResponseEntity<Orders> response = orderController.getOrder("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getOrderId());
        verify(getOrderServiceImpl).findById(1L);
    }

    @Test
    public void getOrderThrowsException() {
        Orders orders = new Orders(1L, 2L, 10.0);
        when(getOrderServiceImpl.findById(1L)).thenThrow(new InvalidOrderException(String.format("Order id %s is not valid", 1)));
        assertThrows(InvalidOrderException.class, () -> orderController.getOrder("1"));

    }

    @Test
    public void getOrderBalancePositive() {
        Orders orders = new Orders(1L, 2L, 10.0);
        OrderBalance orderBalance = new OrderBalance(1L, 10.0);
        when(orderBalanceServiceImpl.getOrderBalanceById(1L)).thenReturn(orderBalance);
        ResponseEntity<OrderBalance> response = orderController.getBalance(String.valueOf(orders.getOrderId()));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getOrderId());
        verify(orderBalanceServiceImpl).getOrderBalanceById(1L);
    }

    @Test
    public void getOrderBalanceThrowsException() {
        Orders orders = new Orders(1L, 2L, 10.0);
        when(orderBalanceServiceImpl.getOrderBalanceById(1L)).thenThrow(new InvalidOrderException(String.format("Order id %s is not valid", 1)));
        assertThrows(InvalidOrderException.class, () -> orderController.getBalance("1"));

    }
}
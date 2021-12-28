package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Customer;
import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.errorHandling.InvalidOrderException;
import com.zooplus.shoppingcart.repository.interfaces.OrderRepository;
import com.zooplus.shoppingcart.service.interfaces.GetPaymentService;
import com.zooplus.shoppingcart.vo.OrderBalance;
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
public class OrderBalanceServiceImplTest {
    @InjectMocks
    OrderBalanceServiceImpl orderBalanceServiceimpl;
    @Mock
    private OrderRepository orderRepositoryImpl;
    @Mock
    private GetPaymentService getPaymentServiceImpl;
    private Payment payment = new Payment(1L, 1L, 10.00);
    private Orders orders = new Orders(1L, 1L, 20.00);
    private Customer customer = new Customer(1L, "Sud", "Pan");

    @Test
    public void testGetOrderBalanceById() {
        when(orderRepositoryImpl.findById(orders.getOrderId())).thenReturn(Optional.of(orders));
        when(getPaymentServiceImpl.getPaymentByOrderId(orders.getOrderId())).thenReturn(List.of(payment));
        OrderBalance orderBalance = orderBalanceServiceimpl.getOrderBalanceById(orders.getOrderId());
        verify(getPaymentServiceImpl).getPaymentByOrderId(orders.getOrderId());
        assertEquals(10, orderBalance.getAmount());
    }

    @Test
    public void testGetOrderBalanceByIdIfOrderNotExist() {
        when(orderRepositoryImpl.findById(orders.getOrderId())).thenThrow(new InvalidOrderException("order Id is not valid"));
        assertThrows(InvalidOrderException.class, () -> orderBalanceServiceimpl.getOrderBalanceById(orders.getOrderId()));
    }

}
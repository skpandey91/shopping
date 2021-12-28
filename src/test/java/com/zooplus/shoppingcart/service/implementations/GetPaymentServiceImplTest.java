package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Customer;
import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.repository.interfaces.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepositoryImpl;
    @InjectMocks
    private GetPaymentServiceImpl getPaymentServiceImpl;
    private Payment payment = new Payment(1L, 1L, 10.00);
    private Orders orders = new Orders(1L, 1L, 20.00);
    private Customer customer = new Customer(1L, "Sud", "Pan");

    @Test
    public void testGetPaymentByOrderId() {
        when(paymentRepositoryImpl.getPaymentsByOrderId(orders.getOrderId())).thenReturn(List.of(payment));
        List<Payment> paymentList = getPaymentServiceImpl.getPaymentByOrderId(orders.getOrderId());
        verify(paymentRepositoryImpl).getPaymentsByOrderId(orders.getOrderId());
        assertEquals(List.of(payment), paymentList);
    }
}
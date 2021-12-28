package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.errorHandling.InvalidOrderException;
import com.zooplus.shoppingcart.repository.interfaces.PaymentRepository;
import com.zooplus.shoppingcart.service.implementations.CreatePaymentServiceImpl;
import com.zooplus.shoppingcart.service.interfaces.GetOrderService;
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
public class CreatePaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepositoryImpl;
    @Mock
    private GetOrderService getOrderServiceImpl;
    @InjectMocks
    private CreatePaymentServiceImpl createPaymentServiceImpl;
    private Payment payment = new Payment(1L, 1L, 10.00);
    private Orders orders = new Orders(1L, 1L, 10.00);

    @Test
    public void testSavePayment() {
        when(getOrderServiceImpl.findById(payment.getOrderId())).thenReturn(orders);
        when(paymentRepositoryImpl.save(payment)).thenReturn(payment);
        Payment payment1 = createPaymentServiceImpl.savePayment(payment);
        verify(paymentRepositoryImpl).save(payment);
        assertEquals(1, payment1.getPaymentId());
    }

    @Test
    public void testSavePaymentIfOrderNotExists() {
        when(getOrderServiceImpl.findById(payment.getOrderId())).thenThrow(new InvalidOrderException("order ID is not valid"));
        assertThrows(InvalidOrderException.class, () -> createPaymentServiceImpl.savePayment(payment));

    }
}
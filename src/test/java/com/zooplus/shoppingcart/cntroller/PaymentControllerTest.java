package com.zooplus.shoppingcart.cntroller;

import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.errorHandling.InvalidOrderException;
import com.zooplus.shoppingcart.service.interfaces.CreatePaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {
    @Mock
    private CreatePaymentService createPaymentServiceImpl;
    @InjectMocks
    private PaymentController paymentController;

    @Test
    public void testPayment() {
        Payment payment = new Payment(1L, 1L, 10.0);
        when(createPaymentServiceImpl.savePayment(payment)).thenReturn(payment);
        ResponseEntity<Payment> response = paymentController.payment(payment);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, response.getBody().getPaymentId());
    }

    @Test
    public void testPaymentThrowsException() {
        Payment payment = new Payment(1L, 1L, 10.0);
        when(createPaymentServiceImpl.savePayment(payment)).thenThrow(new InvalidOrderException("Order id is not valid"));
        assertThrows(InvalidOrderException.class, () -> paymentController.payment(payment));
    }
}
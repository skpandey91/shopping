package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Customer;
import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.errorHandling.InvalidCustomerException;
import com.zooplus.shoppingcart.service.implementations.GetCustomerBalanceServiceImpl;
import com.zooplus.shoppingcart.service.interfaces.GetOrderService;
import com.zooplus.shoppingcart.service.interfaces.GetPaymentService;
import com.zooplus.shoppingcart.service.interfaces.ValidateCustomerService;
import com.zooplus.shoppingcart.vo.CustomerBalance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCustomerBalanceServiceImplTest {
    @InjectMocks
    GetCustomerBalanceServiceImpl getCustomerBalanceServiceImpl;
    @Mock
    private GetOrderService getOrderServiceImpl;
    @Mock
    private ValidateCustomerService validateCustomerServiceImpl;
    @Mock
    private GetPaymentService getPaymentServiceImpl;
    private Payment payment = new Payment(1L, 1L, 10.00);
    private Orders orders = new Orders(1L, 1L, 20.00);
    private Customer customer = new Customer(1L, "Sud", "Pan");

    @Test
    public void testGetCustomerBalance() {
        when(validateCustomerServiceImpl.validateCustomerId(customer.getCustomerId())).thenReturn(true);
        when(getOrderServiceImpl.getOrdersByCustomerId(customer.getCustomerId()))
                .thenReturn(List.of(orders));
        when(getPaymentServiceImpl.getPaymentByOrderId(orders.getOrderId())).thenReturn(List.of(payment));
        CustomerBalance customerBalance = getCustomerBalanceServiceImpl.getCustomerBalance(customer.getCustomerId());
        assertEquals(10.00, customerBalance.getAmount());

    }

    @Test
    public void testGetCustomerBalanceCustomerNotExist() {
        when(validateCustomerServiceImpl.validateCustomerId(customer.getCustomerId())).thenThrow(new InvalidCustomerException("customer id is not valid"));
        assertThrows(InvalidCustomerException.class, () -> getCustomerBalanceServiceImpl.getCustomerBalance(customer.getCustomerId()));
    }
}
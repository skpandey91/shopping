package com.zooplus.shoppingcart.cntroller;

import com.zooplus.shoppingcart.enitity.Customer;
import com.zooplus.shoppingcart.errorHandling.InvalidCustomerException;
import com.zooplus.shoppingcart.service.interfaces.CreateCustomerService;
import com.zooplus.shoppingcart.service.interfaces.GetCustomerBalanceService;
import com.zooplus.shoppingcart.vo.CustomerBalance;
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
public class CustomerControllerTest {
    @Mock
    CreateCustomerService createCustomerServiceImpl;
    @Mock
    GetCustomerBalanceService getCustomerBalanceServiceImpl;
    @InjectMocks
    CustomerController customerController;

    @Test
    public void testRegisterCustomer() {
        Customer customer = new Customer(1L, "sud", "pan");
        when(createCustomerServiceImpl.saveCustomer(customer)).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.register(customer);
        verify(createCustomerServiceImpl).saveCustomer(customer);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(response.getBody().getFirstName(), "sud");
    }

    @Test
    public void testCustomerBalance() {
        Customer customer = new Customer(1L, "sud", "pan");
        CustomerBalance customerBalance = new CustomerBalance(1L, 100.00);
        when(getCustomerBalanceServiceImpl.getCustomerBalance(1L)).thenReturn(customerBalance);
        ResponseEntity<CustomerBalance> response = customerController.getCustomerBalance(String.valueOf(customer.getCustomerId()));
        verify(getCustomerBalanceServiceImpl).getCustomerBalance(customer.getCustomerId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody().getCustomerId(), 1);
    }

    @Test
    public void testCustomerBalanceThrowsException() {
        Customer customer = new Customer(1L, "sud", "pan");
        when(getCustomerBalanceServiceImpl.getCustomerBalance(1L)).thenThrow(new InvalidCustomerException(String.format("Customer id %s is not valid", customer.getCustomerId())));
        assertThrows(InvalidCustomerException.class, () -> customerController.getCustomerBalance(String.valueOf(customer.getCustomerId())));

    }

}
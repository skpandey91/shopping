package com.zooplus.shoppingcart.service.implementations;

import com.zooplus.shoppingcart.enitity.Customer;
import com.zooplus.shoppingcart.repository.interfaces.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepositoryImpl;
    @InjectMocks
    private CreateCustomerServiceImpl createCustomerServiceImpl;
    private Customer customer = new Customer(1L, "Sud", "Pan");

    @Test
    public void testSaveCustomer() {
        when(createCustomerServiceImpl.saveCustomer(customer)).thenReturn(customer);
        Customer customer1 = createCustomerServiceImpl.saveCustomer(customer);
        verify(customerRepositoryImpl).save(customer);
        assertEquals(1, customer1.getCustomerId());

    }
}
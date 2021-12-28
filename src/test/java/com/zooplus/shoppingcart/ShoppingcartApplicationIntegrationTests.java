package com.zooplus.shoppingcart;

import com.zooplus.shoppingcart.enitity.Customer;
import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.service.interfaces.CreateCustomerService;
import com.zooplus.shoppingcart.service.interfaces.CreateOrderService;
import com.zooplus.shoppingcart.service.interfaces.CreatePaymentService;
import com.zooplus.shoppingcart.vo.CustomerBalance;
import com.zooplus.shoppingcart.vo.OrderBalance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShoppingcartApplicationIntegrationTests {

    @Autowired
    private CreateCustomerService createCustomerServiceImpl;
    @Autowired
    private CreateOrderService createOrderServiceImpl;
    @Autowired
    private CreatePaymentService createPaymentServiceImpl;

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }
    @BeforeAll
    public void setup()
    {
        Customer customer = new Customer(null,"Sudhir","Pandey");
        Customer customer1 = new Customer(null,"Aditya","Samant");
        Customer customer2 = new Customer(null,"Chetan","Singh");
        createCustomerServiceImpl.saveCustomer(customer);
        createCustomerServiceImpl.saveCustomer(customer1);
        createCustomerServiceImpl.saveCustomer(customer2);
        Orders orders = new Orders(1L,1L,10.00);
        Orders orders1 = new Orders(2L,2L,10.00);
        createOrderServiceImpl.save(orders);
        createOrderServiceImpl.save(orders1);
        Payment payment = new Payment(null,1L,5.0);
        createPaymentServiceImpl.savePayment(payment);

    }

    @Test
    public void registerCustomer()
    {
        ResponseEntity<Customer> responseEntity = this.restTemplate
                .postForEntity("http://localhost:"+port+"/shoppingcart/register",new Customer(null,"Saket","patel"),Customer.class);
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
    }
    @Test
    public void checkoutOrder()
    {
        String uri = "http://localhost:"+port+"/shoppingcart/checkout";
        HttpHeaders httpHeaders = new HttpHeaders();
        Orders orders = new Orders(5L,1L,50.00);
        HttpEntity<Orders> request = new HttpEntity<>(orders,httpHeaders);
        ResponseEntity<Orders> responseEntity = this.restTemplate
                .postForEntity(uri,request,Orders.class);
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
    }
    @Test
    public void createPayment()
    {
        ResponseEntity<Payment> responseEntity = this.restTemplate
                .postForEntity("http://localhost:"+port+"/shoppingcart/payment",new Payment(null,1L,50.00),Payment.class);
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
    }
    @Test
    public void getCustomerBalance(){
        String id ="1";
        ResponseEntity<CustomerBalance> responseEntity = this.restTemplate
                .getForEntity("http://localhost:"+port+"/shoppingcart/customer/balance/{id}", CustomerBalance.class,id);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void getOrderBalance()
    {
        String id ="1";
        ResponseEntity<OrderBalance> responseEntity = this.restTemplate
                .getForEntity("http://localhost:"+port+"/shoppingcart/order/balance/{id}", OrderBalance.class,id);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void getOrder()
    {
        String id ="1";
        ResponseEntity<Orders> responseEntity = this.restTemplate
                .getForEntity("http://localhost:"+port+"/shoppingcart/order/{id}", Orders.class,id);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

}

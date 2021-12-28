package com.zooplus.shoppingcart.cntroller;

import com.zooplus.shoppingcart.enitity.Customer;
import com.zooplus.shoppingcart.service.interfaces.CreateCustomerService;
import com.zooplus.shoppingcart.service.interfaces.GetCustomerBalanceService;
import com.zooplus.shoppingcart.vo.CustomerBalance;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/shoppingcart")
@Tag(name = "Customer", description = "Customer Api")
public class CustomerController {
    @Autowired
    CreateCustomerService createCustomerServiceImpl;
    @Autowired
    GetCustomerBalanceService getCustomerBalanceServiceImpl;

    @PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
    @Operation(summary = "create customer", description = "register new customer")
    @ApiResponses(value = {@ApiResponse(description = "successful", responseCode = "201"),
            @ApiResponse(description = "failed", responseCode = "400")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> register(@RequestBody Customer customer) {
        log.info("inside customer controller creating payment");
        return ResponseEntity.status(HttpStatus.CREATED).body(createCustomerServiceImpl.saveCustomer(customer));
    }

    @GetMapping("/customer/balance/{id}")
    @Operation(summary = "Customer balance", description = "Get a Customer's balance")
    @ApiResponses(value = {@ApiResponse(description = "successful", responseCode = "200"),
            @ApiResponse(description = "failed", responseCode = "400")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerBalance> getCustomerBalance(@PathVariable("id") String id) {
        log.info("inside customer controller and getting the customer balance ");
        return ResponseEntity.ok(getCustomerBalanceServiceImpl.getCustomerBalance(Long.parseLong(id)));
    }
}
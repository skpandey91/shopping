package com.zooplus.shoppingcart.cntroller;

import com.zooplus.shoppingcart.enitity.Payment;
import com.zooplus.shoppingcart.service.interfaces.CreatePaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/shoppingcart")
@Tag(name = "Payment", description = "Payment Api")
public class PaymentController {
    @Autowired
    private CreatePaymentService createPaymentServiceImpl;

    @PostMapping(value = "/payment", produces = "application/json", consumes = "application/json")
    @Operation(summary = "create payment", description = "Create a new payment against an order")
    @ApiResponses(value = {@ApiResponse(description = "successful", responseCode = "201"),
            @ApiResponse(description = "failed", responseCode = "400")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Payment> payment(@RequestBody Payment payment) {
        log.info("Inside payment controller creating the payment");
        return ResponseEntity.status(HttpStatus.CREATED).body(createPaymentServiceImpl.savePayment(payment));
    }
}
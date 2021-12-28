package com.zooplus.shoppingcart.cntroller;

import com.zooplus.shoppingcart.enitity.Orders;
import com.zooplus.shoppingcart.service.interfaces.CreateOrderService;
import com.zooplus.shoppingcart.service.interfaces.GetOrderService;
import com.zooplus.shoppingcart.service.interfaces.OrderBalanceService;
import com.zooplus.shoppingcart.vo.OrderBalance;
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

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/shoppingcart")
@Tag(name = "Order", description = "Order Api")
public class OrderController {
    @Autowired
    private CreateOrderService createOrderServiceImpl;
    @Autowired
    private GetOrderService getOrderServiceImpl;
    @Autowired
    private OrderBalanceService orderBalanceServiceImpl;

    @PostMapping("/checkout")
    @Operation(summary = "create Order", description = "Create a new order")
    @ApiResponses(value = {@ApiResponse(description = "successful", responseCode = "201"),
            @ApiResponse(description = "failed", responseCode = "400")
    })
    public ResponseEntity<Orders> checkout(@RequestBody @Valid Orders orders) {
        log.info("Inside Order controller and creating the order ");
        return ResponseEntity.status(HttpStatus.CREATED).body(createOrderServiceImpl.save(orders));
    }

    @GetMapping("/order/balance/{id}")
    @Operation(summary = "Order balance", description = "Get balance for an order")
    @ApiResponses(value = {@ApiResponse(description = "successful", responseCode = "200"),
            @ApiResponse(description = "failed", responseCode = "400")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderBalance> getBalance(@PathVariable("id") @Valid String id) {
        log.info("inside the order controller and fetching the order balance");

        return ResponseEntity.ok(orderBalanceServiceImpl.getOrderBalanceById(Long.parseLong(id)));
    }

    @GetMapping("/order/{id}")
    @Operation(summary = "Order", description = "Get details of order")
    @ApiResponses(value = {@ApiResponse(description = "successful", responseCode = "200"),
            @ApiResponse(description = "failed", responseCode = "400")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Orders> getOrder(@PathVariable("id") String id) {
        log.info("inside the order controller fetching the order details");
        Orders orders = getOrderServiceImpl.findById(Long.parseLong(id));
        return ResponseEntity.ok().body(orders);
    }
}
package com.zooplus.shoppingcart.errorHandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {InvalidCustomerException.class, IllegalArgumentException.class, EntityNotFoundException.class, InvalidOrderException.class, InvalidPaymentException.class, OrderNotFoundException.class})
    public ResponseEntity<Object> handlerForInvalidRequest(Throwable exception) {
        log.debug(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

    }
}
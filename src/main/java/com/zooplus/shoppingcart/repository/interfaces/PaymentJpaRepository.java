package com.zooplus.shoppingcart.repository.interfaces;

import com.zooplus.shoppingcart.enitity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT * FROM PAYMENT WHERE ORDER_ID = ?1", nativeQuery = true)
    public List<Payment> getPaymentsByOrderId(long id);

}
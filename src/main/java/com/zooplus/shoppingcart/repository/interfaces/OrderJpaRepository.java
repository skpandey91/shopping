package com.zooplus.shoppingcart.repository.interfaces;

import com.zooplus.shoppingcart.enitity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderJpaRepository extends JpaRepository<Orders, Long> {
    @Query(value = "SELECT * FROM ORDERS WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    public List<Orders> getOrdersByCustomerId(long id);
}
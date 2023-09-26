package com.alx.orderservice.repository;

import com.alx.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}

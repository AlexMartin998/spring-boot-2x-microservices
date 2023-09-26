package com.alx.orderservice.repository;

import com.alx.orderservice.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItems, Long> {

    boolean existsByskuCode(String skuCode);

}

package com.alx.orderservice.service;

import com.alx.orderservice.dto.OrderRequestDto;

public interface OrderService {

    void create(OrderRequestDto orderRequestDto);

}

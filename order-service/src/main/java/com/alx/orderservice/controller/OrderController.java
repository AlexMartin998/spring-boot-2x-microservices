package com.alx.orderservice.controller;

import com.alx.orderservice.dto.OrderRequestDto;
import com.alx.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        orderService.create(orderRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

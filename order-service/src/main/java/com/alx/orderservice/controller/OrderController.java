package com.alx.orderservice.controller;

import com.alx.orderservice.dto.OrderRequestDto;
import com.alx.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public CompletableFuture<Void> placeOrder(@RequestBody OrderRequestDto orderRequest) {
        log.info("Placing Order");
        orderService.create(orderRequest);
        return CompletableFuture.supplyAsync(() -> null);
    }

    public CompletableFuture<String> fallbackMethod(OrderRequestDto orderRequest, RuntimeException runtimeException) {
        log.info("Cannot Place Order Executing Fallback logic");
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time!");
    }

}

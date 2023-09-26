package com.alx.orderservice.service;

import com.alx.orderservice.dto.OrderLineItemsDto;
import com.alx.orderservice.dto.OrderRequestDto;
import com.alx.orderservice.model.Order;
import com.alx.orderservice.model.OrderLineItems;
import com.alx.orderservice.repository.OrderLineItemRepository;
import com.alx.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;


    @Override
    @Transactional
    public void create(OrderRequestDto orderRequestDto) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequestDto.getOrderLineItemList().stream()
                .map(orderLineItemsDto -> {
                    if (orderLineItemRepository.existsByskuCode(orderLineItemsDto.getSkuCode()))
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already registered with that skucode");

                    return mapToDto(orderLineItemsDto, order);
                })
                .toList();


        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }


    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemDto, Order order) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemDto.getPrice());
        orderLineItems.setQuantity(orderLineItemDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItems.setOrder(order);

        return orderLineItems;
    }
}

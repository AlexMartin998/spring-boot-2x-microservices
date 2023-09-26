package com.alx.orderservice.service;

import com.alx.orderservice.dto.OrderLineItemsDto;
import com.alx.orderservice.dto.OrderRequestDto;
import com.alx.orderservice.model.Order;
import com.alx.orderservice.model.OrderLineItems;
import com.alx.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public void create(OrderRequestDto orderRequestDto) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequestDto.getOrderLineItemList().stream()
                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto, order))
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

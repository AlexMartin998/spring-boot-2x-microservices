package com.alx.orderservice.service;

import com.alx.orderservice.dto.InventoryResponseDto;
import com.alx.orderservice.dto.OrderLineItemsDto;
import com.alx.orderservice.dto.OrderRequestDto;
import com.alx.orderservice.model.Order;
import com.alx.orderservice.model.OrderLineItems;
import com.alx.orderservice.repository.OrderLineItemRepository;
import com.alx.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Value("${app.inventory-ms.base-url}")
    private String inventoryMSBaseUrl;

    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final WebClient webClient;


    @Override
    @Transactional
    public void create(OrderRequestDto orderRequestDto) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequestDto.getOrderLineItemList().stream()
                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto, order))
                .toList();

        order.setOrderLineItemsList(orderLineItems);


        // // call inventory service, and place order if product is in stock. WebClient hace una Async Req, para hacerlo Sync usamos .block()
        // build sku-code list
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        InventoryResponseDto[] inventoryResponseDtoArray = webClient.get()
                .uri(
                        inventoryMSBaseUrl.concat("/inventory"),
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class)  // to knows the data type of response
                .block();

        assert inventoryResponseDtoArray != null;
        boolean allProductsInStock = Arrays.stream(inventoryResponseDtoArray)
                .allMatch(InventoryResponseDto::isInStock);  // like  .every(cb)  in JS

        if (!allProductsInStock || inventoryResponseDtoArray.length != skuCodes.size())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product is not in stock, please try again later");


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

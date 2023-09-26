package com.alx.orderservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;

}

package com.alx.orderservice.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "order_line_times")
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}

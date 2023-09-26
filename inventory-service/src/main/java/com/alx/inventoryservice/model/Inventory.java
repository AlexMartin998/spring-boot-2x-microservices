package com.alx.inventoryservice.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuCode;
    private Integer quantity;

}

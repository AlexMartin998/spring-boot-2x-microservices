package com.alx.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductResponseDto {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}

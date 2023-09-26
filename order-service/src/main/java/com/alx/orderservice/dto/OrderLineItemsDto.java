package com.alx.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {

    private Long id;

    @NotBlank
    private String skuCode;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer quantity;

}

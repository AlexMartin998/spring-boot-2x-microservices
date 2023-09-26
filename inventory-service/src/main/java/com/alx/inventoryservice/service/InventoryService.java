package com.alx.inventoryservice.service;

import com.alx.inventoryservice.dto.InventoryResponseDto;

import java.util.List;


public interface InventoryService {

    List<InventoryResponseDto> isInStock(List<String> skuCodeList);

}

package com.alx.inventoryservice.controller;

import com.alx.inventoryservice.dto.InventoryResponseDto;
import com.alx.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    // /inventory?sku=p1&sku=p2
    @GetMapping
    public ResponseEntity<List<InventoryResponseDto>> isInStock(@RequestParam(name = "skuCode") List<String> skuCodeList) {
        return ResponseEntity.ok(inventoryService.isInStock(skuCodeList));
    }

}

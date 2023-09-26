package com.alx.inventoryservice.controller;

import com.alx.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    @GetMapping("{sku-code}")
    public ResponseEntity<Boolean> isInStock(@PathVariable(name = "sku-code") String skuCode) {
        return ResponseEntity.ok(inventoryService.isInStock(skuCode));
    }

}
